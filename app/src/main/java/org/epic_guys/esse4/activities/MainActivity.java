package org.epic_guys.esse4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.epic_guys.esse4.API.API;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Persona;

import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class MainActivity extends AppCompatActivity {

    private void launchLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    private void setProfilePicture() {
        ImageView profilePicture = findViewById(R.id.profile_picture);

        API.getPhoto().thenCompose(photo -> {
            runOnUiThread(() -> profilePicture.setImageBitmap(photo));
            return null;
        });
    }

    private void setBasicInfo(Persona p){

        TextView matricola = findViewById(R.id.text_matricola);

        TextView name = findViewById(R.id.text_name);
        TextView surname = findViewById(R.id.text_surname);

        //TextView corso = findViewById(R.id.corso);
        //TextView anno = findViewById(R.id.anno);

        //TextView part_time = findViewById(R.id.part_time);

        matricola.setText("Matricola: " + p.getUserId());
        name.setText(p.getNome());
        surname.setText(p.getCognome());

    }

    private Pair<String,String> getCredsFromSecurePreferences() throws Exception{
        String matricola;
        String password;

        matricola = SecurePreferences.getStringValue("matricola",this,  "");
        password = SecurePreferences.getStringValue( "password", this, "");

        if (matricola.equals("") || password.equals("")) {
            Log.i("MainActivity", "Secure preferences not set");
            throw new Exception("Secure preferences not set");
        }

        return new Pair<>(matricola, password);
    }

    private void clearPreferences(){
        SecurePreferences.removeValue("matricola", this);
        SecurePreferences.removeValue("password", this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String matricola;
        String password;

        // checks if secure preferences are set, if not launch login activity
        try{
            Pair<String,String> creds = getCredsFromSecurePreferences();
            matricola = creds.first;
            password = creds.second;
        }
        catch (Exception e) {
            Log.i("MainActivity", "Secure preferences not accessible");
            launchLoginActivity();
            return;
        }

        if(API.getLoggedPersona() == null){

                API.login(matricola, password)
                        .exceptionally(e -> {
                            Log.w("MainActivity", e.toString());
                            return false;
                        })
                        .thenCompose(isLogged -> {
                            if (isLogged) {
                                return API.getBasicData();
                            } else {
                                clearPreferences();
                                throw new RuntimeException("Failed to login");
                            }
                        }
                        ).thenAccept(persona -> {
                            runOnUiThread(() -> {
                                setBasicInfo(persona);
                                Toast.makeText(getApplicationContext(), "Bentornato " + API.getLoggedPersona().getNome(), Toast.LENGTH_SHORT).show();
                                Log.i("MainActivity", "Login effettuato");
                            });
                            setProfilePicture();
                        }).exceptionally(e -> {
                            Log.e("MainActivity", e.toString());
                            runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), "Login fallito, proprio come te", Toast.LENGTH_SHORT).show();
                                Log.i("MainActivity", "Login fallito");
                            });
                            launchLoginActivity();
                            return null;
                        });

        } else {
            setProfilePicture();
        }

        //logout button
        findViewById(R.id.btn_logout).setOnClickListener(v -> {
            SecurePreferences.removeValue("matricola", this);
            SecurePreferences.removeValue("password", this);
            launchLoginActivity();
        });
    }
}

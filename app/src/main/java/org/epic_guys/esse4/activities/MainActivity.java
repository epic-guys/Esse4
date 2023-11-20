package org.epic_guys.esse4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String matricola = "";
        String password = "";

        // checks if secure preferences are set, if not launch login activity
        try{
            matricola = SecurePreferences.getStringValue("matricola",this,  "");
            password = SecurePreferences.getStringValue( "password", this, "");
        }
        catch (Exception e) {
            Log.i("MainActivity", "Secure preferences not accessible");
            launchLoginActivity();
            return;
        }

        if (matricola.equals("") || password.equals("")) {
            Log.i("MainActivity", "Secure preferences not set");
            launchLoginActivity();
            return;
        }
        if(API.getLoggedPersona() == null){
            API.login(matricola, password)
                    .exceptionally(e -> {
                        Log.w("MainActivity",e.toString());
                        return false;
                    })
                    .thenCompose(isLogged -> {
                if (isLogged) {
                    Log.i("MainActivity", "Login effettuato, richiedo dati");
                    return API.getBasicData();
                }
                else {
                    SecurePreferences.removeValue("matricola", this);
                    SecurePreferences.removeValue("password", this);
                    throw new RuntimeException("Failed to login");
                }
            });
        } else {
            Persona p = API.getLoggedPersona();
            Log.w("MainActivity", "Persona esiste già: " + p.getNome());
        }

        Log.i("MainActivity", "Login effettuato");
        // Toast.makeText(this, "Benvenuto " + API.getLoggedPersona().getNome(), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);

        ImageView profilePicture = findViewById(R.id.profile_picture);

        API.getPhoto().thenAccept(bitmap -> {
            runOnUiThread(() -> {
                profilePicture.setImageBitmap(bitmap);
            });
        });

        //logout button
        findViewById(R.id.btn_logout).setOnClickListener(v -> {
            SecurePreferences.removeValue("matricola", this);
            SecurePreferences.removeValue("password", this);
            launchLoginActivity();
        });
    }
}

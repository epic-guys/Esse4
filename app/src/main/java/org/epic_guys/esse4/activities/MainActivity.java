package org.epic_guys.esse4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.epic_guys.esse4.API.API;

import org.epic_guys.esse4.R;

import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class MainActivity extends AppCompatActivity {

    private void launchLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String matricola = "";
        String password = "";
        super.onCreate(savedInstanceState);

        /*
         * 1. Check if the user is logged in
         * 2. If not, launch the login activity
         * 3. If yes, show the main activity
         * 4. If the login fails, launch the login activity
         * 5. If the login succeeds, continue with the main activity
         *
         * we know that on the first launch the user will login two times... but it's a feature!
         */

        // checks if secure preferences are set, if not launch login activity
        try{
            matricola = SecurePreferences.getStringValue("matricola",this,  "");
            password = SecurePreferences.getStringValue( "password", this, "");
        }
        catch (Exception e) {
            Log.i("MainActivity", "Secure preferences not set");
            launchLoginActivity();
        }
        if (matricola.equals("") || password.equals("")) {
            launchLoginActivity();
        }else{
            runOnUiThread(() -> {
                Log.i("MainActivity", "Returned to main activity");
            });

            // try to login with saved credentials
            API.login(matricola, password).thenComposeAsync(isLogged -> {
                if (isLogged)
                    return API.getBasicData();
                else {
                    throw new RuntimeException("Failed to login");
                }
            }).thenAccept(persona -> {
                runOnUiThread(() -> {
                    Log.i("MainActivity", "Login effettuato");
                    Toast.makeText(this, "Benvenuto " + persona.getNome(), Toast.LENGTH_SHORT).show();
                });
            }).exceptionally(e -> {
                runOnUiThread(() -> {
                    Log.i("MainActivity", "Login fallito");
                    Toast.makeText(this, "Login fallito", Toast.LENGTH_SHORT).show();
                });

                launchLoginActivity();
                return null;
            });

        setContentView(R.layout.activity_main);

            //logout button
            findViewById(R.id.btn_logout).setOnClickListener(v -> {
                SecurePreferences.setValue("matricola", "", this);
                SecurePreferences.setValue("password", "", this);
                launchLoginActivity();
            });
        }
    }
}
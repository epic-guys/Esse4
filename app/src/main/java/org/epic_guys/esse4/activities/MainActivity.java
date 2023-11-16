package org.epic_guys.esse4.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.epic_guys.esse4.API.API;

import org.epic_guys.esse4.R;

public class MainActivity extends AppCompatActivity {

    private void launchLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if secure preferences are set, if not launch login activity
        try{
            String matricola = SecurePreferences.getStringValue(this, "matricola", null);
            String password = SecurePreferences.getStringValue(this, "password", null);

            if (matricola == null || password == null)
                launchLoginActivity();

            // try to login with saved credentials
            API.login(matricola, password).thenComposeAsync(isLogged -> {
                if (isLogged)
                    return API.getBasicData();
                else {
                    launchLoginActivity();
                    throw new RuntimeException("Failed to login");
                }
            }).thenAccept(persona -> {
                runOnUiThread(() -> {
                    Log.i("MainActivity", "Login effettuato");
                });
            });
        }
        catch (Exception e) {
            launchLoginActivity();
        }

        setContentView(R.layout.activity_main);

        // do all the stuff needed to show the main activity
    }
}
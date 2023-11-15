package org.epic_guys.esse4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.epic_guys.esse4.R;

import org.epic_guys.esse4.API.API;

import android.util.Log;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(view -> {
            EditText matricolaInput = findViewById(R.id.textinput_matricola);
            EditText passwordInput = findViewById(R.id.textinput_password);

            String matricola = matricolaInput.getText().toString();
            String password = passwordInput.getText().toString();

            API.login(matricola, password).thenComposeAsync(isLogged -> {
                if (isLogged)
                    return API.getBasicData();
                else {
                    runOnUiThread(() ->
                            Toast.makeText(getApplicationContext(), "Login fallito, proprio come te", Toast.LENGTH_SHORT).show()
                    );
                    throw new RuntimeException("Failed to login");
                }
            }).thenAccept(persona -> {
                    runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), "Login effettuato", Toast.LENGTH_SHORT).show();
                                Log.i("LoginActivity", "Login effettuato");
                            });
                        Account a = new Account(matricola, "org.epic_guys.esse4");

                        Bundle usrData = new Bundle();

                        AccountManager am = AccountManager.get(this);
                        Log.i("LoginActivity", "AccountManager: " + am.toString());

                        Log.i("LoginActivity", "Adding account");

                        am.addAccountExplicitly(
                                a,
                                password,
                                usrData
                        );// why this operation blocks everything?

                        /*
                        2023-11-15 14:40:35.265  7181-7191  epic_guys.esse4         org.epic_guys.esse4                  W  Cleared Reference was only reachable from finalizer (only reported once)
                        2023-11-15 14:45:27.329  7377-7388  System                  org.epic_guys.esse4                  W  A resource failed to call close.
                        */

                        Log.i("LoginActivity", "Account added");

                        if(am.addAccountExplicitly(
                                a,
                                password,
                                usrData
                        )){
                            Bundle result = new Bundle();
                            result.putString(AccountManager.KEY_ACCOUNT_NAME, matricola);
                            result.putString(AccountManager.KEY_ACCOUNT_TYPE, "org.epic_guys.esse4");
                        }

                        Log.i("LoginActivity", "Account: " + a.toString());

                        Intent mainActivity = new Intent(this, MainActivity.class);
                        startActivity(mainActivity);
                        Log.i("LoginActivity", "Starting MainActivity, finishing LoginActivity");
                        finish();
                    //});

                    //TextView fullname_view = findViewById(R.id.text_fullname);
                    //fullname_view.setText(persona.getNome());
            });

        });
    }
}
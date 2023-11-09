package org.epic_guys.esse4.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.epic_guys.esse4.R;

import javax.net.ssl.HttpsURLConnection;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener((view -> {
            EditText matricolaInput = findViewById(R.id.textinput_matricola);
            EditText passwordInput = findViewById(R.id.textinput_password);

            try {
                URL loginURL = new URL("https", "esse3.unive.it", 443, "/e3rest/api/login/");
                HttpsURLConnection connection = (HttpsURLConnection) loginURL.openConnection();
                Authenticator authenticator; // TODO
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }
}
package org.epic_guys.esse4.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.epic_guys.esse4.R;

import models.API.API;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener((view -> {
            EditText matricolaInput = findViewById(R.id.textinput_matricola);
            EditText passwordInput = findViewById(R.id.textinput_password);

            String matricola = matricolaInput.getText().toString();
            String password = passwordInput.getText().toString();

            try{
                boolean res = API.login(matricola,password);

                if(res){
                    Toast.makeText(this, "Login effettuato", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Login fallito, proprio come te", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            TextView fullname_view = findViewById(R.id.text_fullname);

            fullname_view.setText(API.getBasicData());

        }));
    }
}
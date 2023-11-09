package org.epic_guys.esse4.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import org.epic_guys.esse4.R;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener((view -> {
            EditText matricolaInput = findViewById(R.id.textinput_matricola);
            EditText passwordInput = findViewById(R.id.textinput_password);

            // Solo per test, nella documentazione c'è scritto che il client HTTP
            // deve essere centralizzato
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            String auth = Credentials.basic(
                    matricolaInput.getText().toString(),
                    passwordInput.getText().toString());

            Request request = new Request.Builder()
                    .url("https://esse3.unive.it/e3rest/api/login/")
                    .addHeader("Authorization", auth)
                    .build();

            // Enqueue fa la richiesta in modo asincrono
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    try {
                        // Parso il body in JSON
                        JSONObject jsonBody = new JSONObject(response.body().string());
                        JSONObject jsonUser = jsonBody.getJSONObject("user");
                        String fullname = jsonUser.getString("firstName") + ' ' + jsonUser.getString("lastName");
                        TextView fullname_view = findViewById(R.id.text_fullname);
                        fullname_view.setText(fullname);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }));
    }
}
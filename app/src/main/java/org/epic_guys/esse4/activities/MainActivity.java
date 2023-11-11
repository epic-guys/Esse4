package org.epic_guys.esse4.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import models.API.API;
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

        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccountsByType("org.epic_guys.esse4");
        if (accounts.length == 0) {
            Intent loginActivity = new Intent(this, LoginActivity.class);
            startActivity(loginActivity);
        } else {
            // Manage account, get token, etc.
            // if login fails, delete account and return to login activity
        }

        setContentView(R.layout.activity_main);

        // do all the stuff needed to show the main activity
    }
}
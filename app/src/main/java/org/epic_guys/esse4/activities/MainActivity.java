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

            AccountManager am = AccountManager.get(this);
            Account[] accounts = am.getAccountsByType("org.epic_guys.esse4");
            Log.i("MainActivity", "Accounts: " + accounts.length);
            if (accounts.length == 0) {
                Log.i("MainActivity", "No accounts found");
                launchLoginActivity();
            } else {
                Log.i("MainActivity", "Account found");
                // Manage account, get token, etc.
                // if login fails, delete account and return to login activity
            }

        setContentView(R.layout.activity_main);

        // do all the stuff needed to show the main activity
    }
}
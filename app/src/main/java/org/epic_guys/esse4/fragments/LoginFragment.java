package org.epic_guys.esse4.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.common.Common;
import android.util.Log;
import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class LoginFragment extends Fragment {

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = NavHostFragment.findNavController(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {

            //disable button to prevent multiple requests
            loginButton.setEnabled(false);
            //start loading animation
            Common.startLoading(loginButton, requireView(), R.id.loading);

            Log.i("LoginActivity", "Login Requested from button");

            EditText matricolaInput = view.findViewById(R.id.textinput_matricola);
            EditText passwordInput = view.findViewById(R.id.textinput_password);

            String matricola = matricolaInput.getText().toString();
            String password = passwordInput.getText().toString();

            API.login(matricola, password).thenCompose(isLogged -> {
                if (isLogged)
                    return API.getBasicData();
                else {
                    Toast.makeText(getContext(), "Login fallito", Toast.LENGTH_SHORT).show();

                    Common.stopLoading(loginButton, requireView(), R.id.loading);
                    loginButton.setEnabled(true);
                    throw new RuntimeException("Failed to login");
                }
            }).thenAccept(persona -> {
                    Toast.makeText(getContext(), "Login effettuato", Toast.LENGTH_SHORT).show();
                    Log.i("LoginActivity", "Login effettuato");
                try {
                    SecurePreferences.setValue("matricola", matricola, requireContext());
                    SecurePreferences.setValue("password", password, requireContext());

                    Log.i("LoginActivity", "Account added");
                }
                catch (Exception e) {
                    Log.e("LoginActivity", "Failed to save credentials");
                }

                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build();
                navController.navigate(R.id.action_loginFragment_to_homeFragment, null, navOptions);

                Log.i("LoginActivity", "Starting MainActivity, finishing LoginActivity");
            });

        });
    }
}

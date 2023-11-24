package org.epic_guys.esse4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import de.adorsys.android.securestoragelibrary.SecurePreferences;
import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Persona;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class HomeFragment extends Fragment {
    
    private NavController navController;
    private CompletableFuture<Persona> personaFuture;

    private void launchLoginFragment() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .build();
        navController.navigate(R.id.loginFragment, null, navOptions);
    }
    

    private void setProfilePicture() {
        ImageView profilePicture = getView().findViewById(R.id.profile_picture);

        API.getPhoto().thenCompose(photo -> {
            getActivity().runOnUiThread(() -> profilePicture.setImageBitmap(photo));
            return null;
        });
    }

    private void setBasicInfo(Persona p){

        setProfilePicture();

        TextView matricola = getView().findViewById(R.id.text_matricola);

        TextView name = getView().findViewById(R.id.text_name);
        TextView surname = getView().findViewById(R.id.text_surname);

        //TextView corso = findViewById(R.id.corso);
        //TextView anno = findViewById(R.id.anno);

        //TextView part_time = findViewById(R.id.part_time);

        matricola.setText("Matricola: " + p.getUserId());
        name.setText(p.getNome());
        surname.setText(p.getCognome());


    }

    private Pair<String,String> getCredsFromSecurePreferences() throws Exception{
        String matricola;
        String password;

        matricola = SecurePreferences.getStringValue("matricola",getContext(),  "");
        password = SecurePreferences.getStringValue( "password", getContext(), "");

        if (matricola.equals("") || password.equals("")) {
            Log.i("HomeFragment", "Secure preferences not set");
            throw new Exception("Secure preferences not set");
        }

        return new Pair<>(matricola, password);
    }

    private void clearPreferences(){
        SecurePreferences.removeValue("matricola", getContext());
        SecurePreferences.removeValue("password", getContext());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        navController = NavHostFragment.findNavController(this);

        String matricola;
        String password;

        // checks if secure preferences are set, if not launch login activity
        try{
            Pair<String,String> creds = getCredsFromSecurePreferences();
            matricola = creds.first;
            password = creds.second;
        }
        catch (Exception e) {
            Log.i("HomeFragment", "Secure preferences not accessible");
            launchLoginFragment();
            return;
        }


        if(API.getLoggedPersona() != null) {
            personaFuture = new CompletableFuture<>();
            personaFuture.complete(API.getLoggedPersona());
            return;
        }

        personaFuture = API.login(matricola, password)
                .exceptionally(e -> {
                    Log.w("HomeFragment", e.toString());
                    return false;
                })
                .thenCompose(isLogged -> {
                            if (isLogged) {
                                return API.getBasicData();
                            } else {
                                clearPreferences();
                                throw new RuntimeException("Failed to login");
                            }
                        }
                );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        personaFuture.thenAccept(persona -> {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), "Bentornato " + API.getLoggedPersona().getNome(), Toast.LENGTH_SHORT).show();
                Log.i("HomeFragment", "Login effettuato");
                setBasicInfo(persona);
            });
        }).exceptionally(e -> {
            Log.e("HomeFragment", e.toString());
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), "Login fallito, proprio come te", Toast.LENGTH_SHORT).show();
                Log.i("HomeFragment", "Login fallito");
            });
            launchLoginFragment();
            return null;
        });

        //logout button
        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            SecurePreferences.removeValue("matricola", getContext());
            SecurePreferences.removeValue("password", getContext());
            launchLoginFragment();
        });
    }
}

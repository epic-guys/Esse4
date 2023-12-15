package org.epic_guys.esse4.fragments;

import android.os.Bundle;
import android.util.Log;
import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.Carriera;
import org.epic_guys.esse4.models.Persona;
import java.util.concurrent.CompletableFuture;

public class HomeFragment extends Fragment {
    
    private NavController navController;
    private CompletableFuture<Pair<Persona, Carriera>> basicDataFuture;

    private void launchLoginFragment() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .build();
        navController.navigate(R.id.loginFragment, null, navOptions);
    }



    private void setProfilePicture() {
        ImageView profilePicture = requireView().findViewById(R.id.profile_picture);

        API.getPhoto().thenCompose(photo -> {
            requireActivity().runOnUiThread(() -> profilePicture.setImageBitmap(photo));
            return null;
        });
    }

    private void setBasicInfo(Persona p, Carriera c){

        setProfilePicture();

        requireView().<TextView>findViewById(R.id.data_matricola).setText(p.getUserId());
        requireView().<TextView>findViewById(R.id.text_name).setText(p.getNome());
        requireView().<TextView>findViewById(R.id.text_surname).setText(p.getCognome());

        requireView().<TextView>findViewById(R.id.text_degree).setText(c.getDescrizioneCorsoDiLaurea());
        requireView().<TextView>findViewById(R.id.data_year).setText(String.valueOf(c.getAnnoCorso()));

        if(c.isPartTime()) {
            requireView().findViewById(R.id.text_part_time).setVisibility(View.VISIBLE);
        }


    }

    private Pair<String,String> getCredsFromSecurePreferences() throws Exception{
        String matricola;
        String password;

        matricola = SecurePreferences.getStringValue("matricola", requireContext(),  "");
        password = SecurePreferences.getStringValue( "password", requireContext(), "");

        if (matricola.equals("") || password.equals("")) {
            Log.d("HomeFragment", "Secure preferences not set");
            throw new Exception("Secure preferences not set");
        }

        return new Pair<>(matricola, password);
    }

    private void clearPreferences(){
        SecurePreferences.removeValue("matricola", requireContext());
        SecurePreferences.removeValue("password", requireContext());
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
            Log.d("HomeFragment", "Secure preferences not accessible");
            launchLoginFragment();
            return;
        }


        if(API.getLoggedPersona() != null) {
            basicDataFuture = new CompletableFuture<>();
            basicDataFuture.complete(new Pair<>(API.getLoggedPersona(), API.getCarriera()));
            return;
        }

        basicDataFuture = API.login(matricola, password)
                .exceptionally(e -> {
                    Log.d("HomeFragment", e.toString());
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
        Common.startLoading(requireView().findViewById(R.id.window), requireView(), R.id.loading);

        basicDataFuture.thenAccept(personaCarrieraPair -> requireActivity().runOnUiThread(() -> {
            Toast.makeText(getContext(), "Bentornat[ao] studente(?:ssa|)" /* + API.getLoggedPersona().getNome() */, Toast.LENGTH_SHORT).show();
            setBasicInfo(personaCarrieraPair.first, personaCarrieraPair.second);
            Common.stopLoading(requireView().findViewById(R.id.window), requireView(), R.id.loading);
        })).exceptionally(e -> {
            Log.e("HomeFragment", e.toString());
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), "Login fallito", Toast.LENGTH_SHORT).show();
                Log.d("HomeFragment", "Login fallito");

            });
            launchLoginFragment();
            return null;
        });

        //logout button
        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            SecurePreferences.removeValue("matricola", requireContext());
            SecurePreferences.removeValue("password", requireContext());
            launchLoginFragment();
        });

        //study plan button
        view.findViewById(R.id.card).setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_studyPlanFragment));

        //study book button
        view.findViewById(R.id.btn_student_book).setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_studentBookFragment));

        view.findViewById(R.id.btn_exams).setOnClickListener(v -> navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToAppelliFragment(
                        API.getCarriera().getIdCarriera(),
                        -1
                )
        ));
    }
}

package com.example.athena.RegistrationFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

//import com.example.athena.EntrantAndOrganizerActivities.entrantAndOrganizerHomeActivity;
import com.example.athena.EntrantAndOrganizerFragments.entrantAndOrganizerHomeFragment;
import com.example.athena.GeneralActivities.MainActivity;
import com.example.athena.R;
import com.example.athena.databinding.SignUpFragmentBinding;
import com.example.athena.databinding.SigninScreenFragmentBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class signUpFragment extends Fragment {
    private SignUpFragmentBinding binding;

    public signUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ///Initializer the binding object
        binding = SignUpFragmentBinding.inflate(inflater, container, false);
        ///Inflates the layout for the fragment
        View signUpScreenLayout = binding.getRoot();

        ImageButton regButton = signUpScreenLayout.findViewById(R.id.register_button);

        return signUpScreenLayout;

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Retrieve values from EditText fields
                String name = binding.editNameReg.getText().toString().trim();
                String email = binding.editEmailReg.getText().toString().trim();
                String number = binding.editNumberReg.getText().toString().trim();

                // Check if fields are empty
                if (name.isEmpty()) {
                    binding.editNameReg.setError("Name cannot be empty");
                    binding.editNameReg.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    binding.editEmailReg.setError("Email cannot be empty");
                    binding.editEmailReg.requestFocus();
                    return;
                }

                Bundle mainActivity = getArguments();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                HashMap<String, Object> data = new HashMap<>();
                data.put("name", name);
                data.put("email", email);
                data.put("phone", number);

                assert mainActivity != null;
                db.collection("Users").document(Objects.requireNonNull(mainActivity.getString("deviceID"))).set(data);

                entrantAndOrganizerHomeFragment homeScreen = new entrantAndOrganizerHomeFragment();

                // If all fields are filled, proceed with action
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                homeScreen.setArguments(mainActivity);
                transaction.replace(R.id.content_layout, homeScreen);
                transaction.commit();
            }
        });
    }
}



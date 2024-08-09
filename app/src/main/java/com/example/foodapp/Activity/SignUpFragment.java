package com.example.foodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    private static final String TAG = "SignUpFragment";
    private FragmentSignUpBinding binding;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        database=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



        binding.signupBtn.setOnClickListener(v -> {
            String email = binding.userEdit.getText().toString();
            String password = binding.passwordEdit.getText().toString();


            if (password.length() < 6) {
                Toast.makeText(getActivity(), "Your password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create user with email and password using Firebase Auth
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    Log.i(TAG, "onComplete: Signup successful");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else {

                    Log.e(TAG, "onComplete: Signup failed", task.getException());
                    Toast.makeText(getActivity(), "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}

package com.example.foodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentLogInBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LogInFragment extends Fragment {

    FirebaseAuth mAuth;
    private static final String TAG = "LogInFragment";
    private FragmentLogInBinding binding;

    public LogInFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLogInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        binding.loginBtn.setOnClickListener(v -> {
            String email = binding.userEdit.getText().toString();
            String password = binding.passwordEdit.getText().toString();

            if (password.length() < 6) {
                Toast.makeText(getActivity(), "Your password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sign in user with email and password using Firebase Auth
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    Log.i(TAG, "onComplete: Login successful");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else {

                    Log.e(TAG, "onComplete: Login failed", task.getException());
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

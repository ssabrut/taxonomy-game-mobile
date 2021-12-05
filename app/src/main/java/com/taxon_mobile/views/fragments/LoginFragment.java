package com.taxon_mobile.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.viewmodels.AuthViewModel;
import com.taxon_mobile.views.activities.RegisterActivity;

public class LoginFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TextInputLayout login_input_email, login_input_password;
    private Button login_btn;
    private AuthViewModel viewModel;
    private TextView login_change_auth;

    public LoginFragment() {
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login_input_email = view.findViewById(R.id.login_input_email);
        login_input_password = view.findViewById(R.id.login_input_password);
        login_btn = view.findViewById(R.id.login_btn);
        login_change_auth = view.findViewById(R.id.login_change_auth);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = login_input_email.getEditText().getText().toString().trim();
                String password = login_input_password.getEditText().getText().toString().trim();
                viewModel.login(email, password);
                viewModel.getLoginDetails().observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(LoginResponse loginResponse) {
                        Toast.makeText(getContext(), loginResponse.getToken(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        login_change_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
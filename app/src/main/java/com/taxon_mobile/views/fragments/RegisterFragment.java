package com.taxon_mobile.views.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.viewmodels.AuthViewModel;

public class RegisterFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private Toolbar register_toolbar;
    private TextInputLayout register_input_name, register_input_username, register_input_email, register_input_password;
    private Button register_btn;
    private AuthViewModel viewModel;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        register_toolbar = view.findViewById(R.id.register_toolbar);
        register_input_name = view.findViewById(R.id.register_input_name);
        register_input_username = view.findViewById(R.id.register_input_username);
        register_input_email = view.findViewById(R.id.register_input_email);
        register_input_password = view.findViewById(R.id.register_input_password);
        register_btn = view.findViewById(R.id.register_btn);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = register_input_name.getEditText().getText().toString().trim();
                String username = register_input_username.getEditText().getText().toString().trim();
                String email = register_input_email.getEditText().getText().toString().trim();
                String password = register_input_password.getEditText().getText().toString().trim();
                viewModel.register(name, username, email, password);
                viewModel.getRegisterDetails().observe(getViewLifecycleOwner(), new Observer<RegisterResponse>() {
                    @Override
                    public void onChanged(RegisterResponse registerResponse) {
                        Toast.makeText(getContext(), registerResponse.getToken(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}
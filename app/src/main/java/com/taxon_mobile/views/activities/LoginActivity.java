package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.viewmodels.AuthViewModel;
import com.taxon_mobile.views.MainActivity;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout login_email_input, login_password_input;
    private CardView login_btn;
    private TextView login_change_register;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email_input = findViewById(R.id.login_email_input);
        login_password_input = findViewById(R.id.login_password_input);
        login_btn = findViewById(R.id.login_btn);
        login_change_register = findViewById(R.id.login_change_register);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = login_email_input.getEditText().getText().toString().trim();
                String password = login_password_input.getEditText().getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    viewModel.login(email, password);
                    viewModel.getLoginDetails().observe(LoginActivity.this, new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {
                            if (loginResponse.getStatus_code() == 200) {
                                try {
                                    MainActivity.user = loginResponse.getUser();
                                    MainActivity.token = loginResponse.getToken();
                                    MainActivity.power = MainActivity.user.getStat().getPower();
                                    MainActivity.evo = MainActivity.user.getStat().getEvo();
                                    MainActivity.dna = MainActivity.user.getStat().getDna();
                                    MainActivity.point = MainActivity.user.getStat().getPoint();
                                    SharedPreferences sp = getSharedPreferences("UserStat", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putInt("power", MainActivity.power);
                                    editor.putInt("evo", MainActivity.evo);
                                    editor.putInt("dna", MainActivity.dna);
                                    editor.putInt("point", MainActivity.point);
                                    editor.putString("email", email);
                                    editor.putString("password", password);
                                    editor.commit();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
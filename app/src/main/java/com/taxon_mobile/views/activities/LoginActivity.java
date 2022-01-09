package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.User;
import com.taxon_mobile.viewmodels.AuthViewModel;
import com.taxon_mobile.viewmodels.LogViewModel;
import com.taxon_mobile.views.MainActivity;

import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout login_email_input, login_password_input;
    private CardView login_btn;
    private TextView login_change_register;
    private AuthViewModel viewModel;
    private LogViewModel logViewModel;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getBaseContext();
        login_email_input = findViewById(R.id.login_email_input);
        login_password_input = findViewById(R.id.login_password_input);
        login_btn = findViewById(R.id.login_btn);
        login_change_register = findViewById(R.id.login_change_register);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);
        formValidation();

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
                            try {
                                if (loginResponse.getStatus_code() == 200) {
                                    logViewModel.log("Bearer " + loginResponse.getToken(), "User", "User id : " + loginResponse.getUser().getId() + " logged in");
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
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    login_email_input.setError("Email tidak boleh kosong!");
                    login_password_input.setError("Password tidak boleh kosong");
                }
            }
        });

        login_change_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void formValidation() {
        login_email_input.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = login_email_input.getEditText().getText().toString().trim();

                if (email.isEmpty()) {
                    login_email_input.setError("Email tidak boleh kosong!");
                } else {
                    login_email_input.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        login_password_input.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = login_password_input.getEditText().getText().toString().trim();

                if (password.isEmpty()) {
                    login_password_input.setError("Password tidak boleh kosong");
                } else {
                    login_password_input.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
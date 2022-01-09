package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.viewmodels.AuthViewModel;
import com.taxon_mobile.viewmodels.LogViewModel;
import com.taxon_mobile.views.MainActivity;

public class RegisterAccountActivity extends AppCompatActivity {

    private String name, school, city, birthYear;
    private Intent intent;
    private Toolbar register_account_back_btn;
    private TextInputLayout register_input_username, register_input_email, register_input_password;
    private Button register_btn;
    private AuthViewModel viewModel;
    private LogViewModel logViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        intent = getIntent();
        name = intent.getStringExtra("name");
        school = intent.getStringExtra("school");
        city = intent.getStringExtra("city");
        birthYear = intent.getStringExtra("birthyear");
        register_account_back_btn = findViewById(R.id.register_account_back_btn);
        register_input_username = findViewById(R.id.register_input_username);
        register_input_email = findViewById(R.id.register_input_email);
        register_input_password = findViewById(R.id.register_input_password);
        register_btn = findViewById(R.id.register_btn);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);

        register_account_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = register_input_username.getEditText().getText().toString().trim();
                String email = register_input_email.getEditText().getText().toString().trim();
                String password = register_input_password.getEditText().getText().toString().trim();

                if (!username.isEmpty() || !email.isEmpty() || !password.isEmpty()) {
                    viewModel.register(name, username, school, city, birthYear, email, password);
                    viewModel.getRegisterDetails().observe(RegisterAccountActivity.this, new Observer<RegisterResponse>() {
                        @Override
                        public void onChanged(RegisterResponse registerResponse) {
                            if (registerResponse.getStatus_code() == 201) {
                                String email = registerResponse.getUser().getEmail();
                                String password = register_input_password.getEditText().getText().toString().trim();
                                viewModel.login(email, password);
                                viewModel.getLoginDetails().observe(RegisterAccountActivity.this, new Observer<LoginResponse>() {
                                    @Override
                                    public void onChanged(LoginResponse loginResponse) {
                                        if (loginResponse.getStatus_code() == 200) {
                                            logViewModel.log("Bearer " + loginResponse.getToken(), "User", "User id : " + loginResponse.getUser().getId() + " registered");
                                            logViewModel.log("Bearer " + loginResponse.getToken(), "User", "User id : " + loginResponse.getUser().getId() + " logged in");
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
                                                Intent intent = new Intent(RegisterAccountActivity.this, MainActivity.class);
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
        });
    }
}
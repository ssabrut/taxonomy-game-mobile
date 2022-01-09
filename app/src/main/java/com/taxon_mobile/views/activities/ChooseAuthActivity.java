package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.taxon_mobile.R;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.viewmodels.AuthViewModel;
import com.taxon_mobile.viewmodels.LogViewModel;
import com.taxon_mobile.views.MainActivity;

public class ChooseAuthActivity extends AppCompatActivity {

    private CardView choose_login, choose_register;
    private SharedPreferences sp;
    private AuthViewModel viewModel;
    private LogViewModel logViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_auth);
        choose_login = findViewById(R.id.choose_login);
        choose_register = findViewById(R.id.choose_register);
        sp = getSharedPreferences("UserStat", MODE_PRIVATE);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);

        choose_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAuthActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        choose_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAuthActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sp.contains("email")) {
            String email = sp.getString("email", "");
            String password = sp.getString("password", "");
            viewModel.login(email, password);
            viewModel.getLoginDetails().observe(ChooseAuthActivity.this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    if (loginResponse.getStatus_code() == 200) {
                        logViewModel.log("Bearer " + loginResponse.getToken(), "User", "User id: " + loginResponse.getUser().getId() + " logged in");
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
                            Intent intent = new Intent(ChooseAuthActivity.this, MainActivity.class);
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
}
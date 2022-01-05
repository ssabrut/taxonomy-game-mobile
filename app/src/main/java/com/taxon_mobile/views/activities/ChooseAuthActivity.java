package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.taxon_mobile.R;

public class ChooseAuthActivity extends AppCompatActivity {

    private CardView choose_login, choose_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_auth);
        choose_login = findViewById(R.id.choose_login);
        choose_register = findViewById(R.id.choose_register);

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
}
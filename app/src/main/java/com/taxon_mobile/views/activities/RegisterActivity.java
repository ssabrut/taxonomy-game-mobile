package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private Calendar calendar;
    private int day, month, year;
    private StringBuilder birthYear;
    private TextInputLayout register_input_birthyear, register_input_name, register_input_school, register_input_city;
    private Toolbar register_back_btn;
    private Button register_next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        register_input_name = findViewById(R.id.register_input_name);
        register_input_school = findViewById(R.id.register_input_school);
        register_input_city = findViewById(R.id.register_input_city);
        register_input_birthyear = findViewById(R.id.register_input_birthyear);
        register_back_btn = findViewById(R.id.register_back_btn);
        register_next_btn = findViewById(R.id.register_next_btn);

        register_input_birthyear.getEditText().setFocusable(false);

        register_input_birthyear.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });

        register_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = register_input_name.getEditText().getText().toString().trim();
                String school = register_input_school.getEditText().getText().toString().trim();
                String city = register_input_city.getEditText().getText().toString().trim();
                String birthYear = register_input_birthyear.getEditText().getText().toString().trim();

                if (!name.isEmpty() || !school.isEmpty() || !city.isEmpty() || !birthYear.isEmpty()) {
                    Intent intent = new Intent(getBaseContext(), RegisterAccountActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("school", school);
                    intent.putExtra("city", city);
                    intent.putExtra("birthyear", birthYear);
                    startActivity(intent);
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(200);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 200) {
            return new DatePickerDialog(this, dateSetListener, year, month, day);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            birthYear = new StringBuilder().append(i2).append("/").append(i1).append("/").append(i);
            register_input_birthyear.getEditText().setText(birthYear.toString());
        }
    };
}
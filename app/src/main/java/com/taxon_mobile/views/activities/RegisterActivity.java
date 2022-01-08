package com.taxon_mobile.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private Calendar calendar;
    private int day, month, year;
    private StringBuilder birthYear;
    private TextInputLayout register_input_birthyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        register_input_birthyear = findViewById(R.id.register_input_birthyear);

        register_input_birthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
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
        }
    };
}
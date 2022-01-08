package com.taxon_mobile.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taxon_mobile.R;
import com.taxon_mobile.helpers.IOnBackPressed;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.models.User;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView main_bottom_navigation;
    private NavHostFragment main_fragment_container;
    private NavController controller;

    public static LoginResponse.User user;
    public static String token = "";
    public static int power = 0;
    public static int evo = 0;
    public static int dna = 0;
    public static int point = 0;
    public static int counter = 0;
    private UserStatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(UserStatViewModel.class);
        main_bottom_navigation = findViewById(R.id.main_bottom_navigation);
        main_fragment_container = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        controller = main_fragment_container.getNavController();
        NavigationUI.setupWithNavController(main_bottom_navigation, controller);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.saveUserStat("Bearer " + this.token, this.power, this.evo, this.dna, this.point);
        SharedPreferences sp = this.getSharedPreferences("UserStat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("power", this.power);
        editor.putInt("evo", this.evo);
        editor.putInt("dna", this.dna);
        editor.putInt("point", this.point);
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = this.getSharedPreferences("UserStat", MODE_PRIVATE);
        power = sp.getInt("power", 0);
        evo = sp.getInt("evo", 0);
        dna = sp.getInt("dna", 0);
        point = sp.getInt("point", 0);
        MainFragment.main_user_click_power.setText(String.valueOf(power));
        MainFragment.main_user_dna.setText(String.valueOf(evo));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.quizFragment);
        if (counter == 0) {
            if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPresed()) {
                counter = 1;
                super.onBackPressed();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Keluar");
            builder.setMessage("Keluar dan simpan data?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    viewModel.saveUserStat("Bearer " + token, power, evo, dna, point);
                    SharedPreferences sp = getSharedPreferences("UserStat", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("power", power);
                    editor.putInt("evo", evo);
                    editor.putInt("dna", dna);
                    editor.putInt("point", point);
                    editor.commit();
                    finish();
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


    public static void saveUserStat() {

    }
}
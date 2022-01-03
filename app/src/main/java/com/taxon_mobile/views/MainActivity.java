package com.taxon_mobile.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taxon_mobile.R;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.fragments.LoginFragment;
import com.taxon_mobile.views.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView main_bottom_navigation;
    private NavHostFragment main_fragment_container;
    private NavController controller;

    public static int power = 0;
    public static int evo = 0;
    public static int dna = 0;
    public static int point = 0;

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
        viewModel.saveUserStat("Bearer " + LoginFragment.token, power, evo, dna, point);
        SharedPreferences sp = this.getSharedPreferences("UserStat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("power", power);
        editor.putInt("evo", evo);
        editor.putInt("dna", dna);
        editor.putInt("point", point);
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
        super.onBackPressed();
        viewModel.saveUserStat("Bearer " + LoginFragment.token, power, evo, dna, point);
        SharedPreferences sp = this.getSharedPreferences("UserStat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("power", power);
        editor.putInt("evo", evo);
        editor.putInt("dna", dna);
        editor.putInt("point", point);
        editor.commit();
    }
}
package com.taxon_mobile.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taxon_mobile.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView main_bottom_navigation;
    private NavHostFragment main_fragment_container;
    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_bottom_navigation = findViewById(R.id.main_bottom_navigation);
        main_fragment_container = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        controller = main_fragment_container.getNavController();
        NavigationUI.setupWithNavController(main_bottom_navigation, controller);
    }
}
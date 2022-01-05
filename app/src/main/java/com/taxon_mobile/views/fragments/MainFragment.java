package com.taxon_mobile.views.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taxon_mobile.R;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.User;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.MainActivity;

public class MainFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int duration = 120000;
    private String mParam1;
    private String mParam2;

    private ConstraintLayout main_canvas;
    private ImageView main_earth, main_earth_icon, main_water_icon, main_biome_bg;
    public static TextView main_user_click_power, main_user_dna;
    private CardView main_upgrade_user_click_power_btn;
    private UserStatViewModel viewModel;

    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        main_canvas = view.findViewById(R.id.main_canvas);
        main_earth = view.findViewById(R.id.main_earth);
        main_user_click_power = view.findViewById(R.id.main_user_click_power);
        main_user_dna = view.findViewById(R.id.main_user_dna);
        main_earth_icon = view.findViewById(R.id.main_earth_icon);
        main_water_icon = view.findViewById(R.id.main_water_icon);
        main_biome_bg = view.findViewById(R.id.main_biome_bg);
        main_upgrade_user_click_power_btn = view.findViewById(R.id.main_upgrade_user_click_power_btn);
        viewModel = new ViewModelProvider(this).get(UserStatViewModel.class);

        main_user_click_power.setText(String.valueOf(MainActivity.power));
        main_user_dna.setText(String.valueOf(MainActivity.evo));

        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotate.setDuration(duration);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        main_earth.startAnimation(rotate);

        main_canvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.evo += Integer.parseInt(main_user_click_power.getText().toString());
                main_user_dna.setText(String.valueOf(MainActivity.evo));
            }
        });

        main_upgrade_user_click_power_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.token != "") {
                    viewModel.upgradePower("Bearer " + MainActivity.token);
                    viewModel.getUpgradePowerDetails().observe(getViewLifecycleOwner(), new Observer<User.Stat>() {
                        @Override
                        public void onChanged(User.Stat stat) {
                            MainActivity.power = stat.getPower();
                            main_user_click_power.setText(String.valueOf(stat.getPower()));
                        }
                    });
                }
            }
        });

        main_earth_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_biome_bg.setImageResource(R.drawable.galaxy);
                main_earth.setImageResource(R.drawable.earth);
            }
        });

        main_water_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.point < 20) {
                    Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_quizFragment);
                } else {
                    main_biome_bg.setImageResource(R.drawable.ocean_floor);
                    main_earth.setImageResource(0);
                }
            }
        });

        return view;
    }
}
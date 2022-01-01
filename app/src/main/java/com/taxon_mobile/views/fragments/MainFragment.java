package com.taxon_mobile.views.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

public class MainFragment extends Fragment {

    private int power;
    private int evo;
    private int dna;
    private int point;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int duration = 120000;
    private String mParam1;
    private String mParam2;

    private ConstraintLayout main_canvas;
    private ImageView main_earth;
    private TextView main_user_click_power, main_user_dna;
    private CardView main_upgrade_user_click_power_btn;

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
        main_upgrade_user_click_power_btn = view.findViewById(R.id.main_upgrade_user_click_power_btn);

        main_user_click_power.setText(String.valueOf(power));
        main_user_dna.setText(String.valueOf(dna));

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
                dna += power;
                main_user_dna.setText(String.valueOf(dna));
            }
        });

        return view;
    }
}
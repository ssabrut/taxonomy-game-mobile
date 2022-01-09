package com.taxon_mobile.views.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taxon_mobile.R;
import com.taxon_mobile.models.LogoutResponse;
import com.taxon_mobile.models.User;
import com.taxon_mobile.viewmodels.AuthViewModel;
import com.taxon_mobile.viewmodels.LogViewModel;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.MainActivity;
import com.taxon_mobile.views.activities.LoginActivity;

public class MainFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int duration = 120000;
    private String mParam1;
    private String mParam2;

    private ConstraintLayout main_canvas;
    private ImageView main_earth, main_earth_icon, main_biome_bg, main_setting_btn;
    public static TextView main_user_click_power, main_user_dna, quiz_content;
    private CardView main_upgrade_user_click_power_btn, earth_btn, sea_btn;
    private Dialog settingDialog, quizDialog, biome_dialog;
    private Button option_logout_btn;
    private View foo;

    private UserStatViewModel viewModel;
    private LogViewModel logViewModel;

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
        foo = view;
        main_canvas = view.findViewById(R.id.main_canvas);
        main_earth = view.findViewById(R.id.main_earth);
        main_setting_btn = view.findViewById(R.id.main_setting_btn);
        main_user_click_power = view.findViewById(R.id.main_user_click_power);
        main_user_dna = view.findViewById(R.id.main_user_dna);
        main_earth_icon = view.findViewById(R.id.main_earth_icon);
        main_biome_bg = view.findViewById(R.id.main_biome_bg);
        main_upgrade_user_click_power_btn = view.findViewById(R.id.main_upgrade_user_click_power_btn);
        viewModel = new ViewModelProvider(this).get(UserStatViewModel.class);
        logViewModel = new ViewModelProvider(this).get(LogViewModel.class);
        settingDialog = new Dialog(getActivity());
        quizDialog = new Dialog(getActivity());
        biome_dialog = new Dialog(getActivity());
        settingDialog();
        biomeDialog();

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
                MainActivity.evo += MainActivity.power;
                main_user_dna.setText(String.valueOf(MainActivity.evo));
            }
        });

        main_upgrade_user_click_power_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.evo >= MainActivity.power) {
                    MainActivity.evo -= MainActivity.power;
                    MainActivity.power++;
                    viewModel.saveUserStat("Bearer " + MainActivity.token, MainActivity.power, MainActivity.evo, MainActivity.dna, MainActivity.point);

                    viewModel.upgradePower("Bearer " + MainActivity.token);
                    viewModel.getUpgradePowerDetails().observe(getViewLifecycleOwner(), new Observer<User.Stat>() {
                        @Override
                        public void onChanged(User.Stat stat) {
                            main_user_dna.setText(String.valueOf(MainActivity.evo));
                            main_user_click_power.setText(String.valueOf(MainActivity.power));
                            logViewModel.log("Bearer " + MainActivity.token, "UserStat", "User id : " + MainActivity.user.getId() + " UserStat evo subtracted by " + MainActivity.power);
                            logViewModel.log("Bearer " + MainActivity.token, "UserStat", "User id : " + MainActivity.user.getId() + " UserStat power added by 1");
                        }
                    });
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Evo tidak cukup!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        main_earth_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biome_dialog.show();
            }
        });


        main_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingDialog.show();
            }
        });

        return view;
    }

    private void settingDialog() {
        settingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        settingDialog.setContentView(R.layout.dialog_option);
        settingDialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        settingDialog.setCancelable(true);

        option_logout_btn = settingDialog.findViewById(R.id.option_logout_btn);
        option_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logViewModel.log("Bearer " + MainActivity.token, "User", "User id : " + MainActivity.user.getId() + " logged out");
                AuthViewModel authViewModel = new ViewModelProvider(getActivity()).get(AuthViewModel.class);
                authViewModel.logout("Bearer " + MainActivity.token);
                authViewModel.getLogoutDetail().observe(getViewLifecycleOwner(), new Observer<LogoutResponse>() {
                    @Override
                    public void onChanged(LogoutResponse logoutResponse) {
                        if (logoutResponse.getStatus() == 200) {
                            SharedPreferences sp = getContext().getSharedPreferences("UserStat", Context.MODE_PRIVATE);
                            sp.edit().remove("email").commit();
                            sp.edit().remove("password").commit();
                            sp.edit().remove("power").commit();
                            sp.edit().remove("evo").commit();
                            sp.edit().remove("dna").commit();
                            sp.edit().remove("point").commit();
                            getActivity().finish();
                            Intent intent = new Intent(getContext().getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private void quizDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Oops, point kamu kurang");
        builder.setMessage("Saat ini point kamu " + MainActivity.point + ", untuk akses bioma selanjutnya kamu butuh 20 point!");
        builder.setNegativeButton(R.string.cancel_quiz_dialog_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton(R.string.accept_quiz_dialog_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Navigation.findNavController(foo).navigate(R.id.action_mainFragment_to_quizFragment);
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void biomeDialog() {
        biome_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        biome_dialog.setContentView(R.layout.dialog_biome);
        biome_dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        biome_dialog.setCancelable(true);

        earth_btn = biome_dialog.findViewById(R.id.earth_btn);
        sea_btn = biome_dialog.findViewById(R.id.sea_btn);
        earth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_biome_bg.setImageResource(R.drawable.galaxy);
                main_earth.setImageResource(R.drawable.earth);
                biome_dialog.dismiss();
            }
        });

        sea_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.point < 20) {
                    quizDialog();
                } else {
                    main_biome_bg.setImageResource(R.drawable.ocean_floor);
                    main_earth.setImageResource(0);
                }
                biome_dialog.dismiss();
            }
        });
    }
}
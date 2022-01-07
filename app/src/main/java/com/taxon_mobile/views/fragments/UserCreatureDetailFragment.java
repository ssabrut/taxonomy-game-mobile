package com.taxon_mobile.views.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taxon_mobile.R;
import com.taxon_mobile.helpers.Const;
import com.taxon_mobile.models.ShowUserCreature;
import com.taxon_mobile.models.UserCreature;
import com.taxon_mobile.viewmodels.SpeciesViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.List;

public class UserCreatureDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView creature_detail_image, creature_detail_expand_img;
    private CardView creature_detail_card;
    private TextView creature_detail_common_name, creature_detail_description, creature_detail_genus, creature_detail_family, creature_detail_order, creature_detail_class, creature_detail_phylum, creature_detail_kingdom, creature_detail_domain, creature_detail_species, creature_detail_evolution;
    private SpeciesViewModel viewModel;
    private List<UserCreature.UserCreatures.Evolutions> creatureEvolutions;
    private LinearLayout creature_evolution_layout;
    private int speciesId, counter;

    public UserCreatureDetailFragment() {
        // Required empty public constructor
    }

    public static UserCreatureDetailFragment newInstance(String param1, String param2) {
        UserCreatureDetailFragment fragment = new UserCreatureDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_creature_detail, container, false);
        counter = 0;
        creature_detail_image = view.findViewById(R.id.creature_detail_image);
        creature_detail_expand_img = view.findViewById(R.id.creature_detail_expand_img);
        creature_detail_common_name = view.findViewById(R.id.creature_detail_common_name);
        creature_detail_description = view.findViewById(R.id.creature_detail_description);
        creature_detail_species = view.findViewById(R.id.creature_detail_species);
        creature_detail_genus = view.findViewById(R.id.creature_detail_genus);
        creature_detail_family = view.findViewById(R.id.creature_detail_family);
        creature_detail_order = view.findViewById(R.id.creature_detail_order);
        creature_detail_class = view.findViewById(R.id.creature_detail_class);
        creature_detail_phylum = view.findViewById(R.id.creature_detail_phylum);
        creature_detail_kingdom = view.findViewById(R.id.creature_detail_kingdom);
        creature_detail_domain = view.findViewById(R.id.creature_detail_domain);
        creature_detail_card = view.findViewById(R.id.creature_detail_card);
        creature_evolution_layout = view.findViewById(R.id.creature_evolution_layout);
        creature_detail_evolution = view.findViewById(R.id.creature_detail_evolution);

        creature_detail_card.setBackgroundResource(R.drawable.user_creature_detail_card);
        viewModel = new ViewModelProvider(this).get(SpeciesViewModel.class);
        speciesId = getArguments().getInt("speciesId");
        creatureEvolutions = getArguments().getParcelableArrayList("creatureEvolutions");
        viewModel.showCreature("Bearer " + MainActivity.token, String.valueOf(speciesId));
        viewModel.getShowCreatureDetail().observe(getViewLifecycleOwner(), showCreatureDetail);

        creature_detail_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = creature_detail_card.getLayoutParams();
                if (counter == 0) {
                    params.height = 1024;
                    creature_detail_expand_img.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    counter = 1;
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    creature_detail_expand_img.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    counter = 0;
                }

                creature_detail_card.setLayoutParams(params);

                if (counter == 1) {
                    for (int i = 0; i < creatureEvolutions.size(); i++) {
                        CardView creature_evolution_card = new CardView(getActivity().getApplicationContext());
                        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );

                        layoutParams.setMargins(0, 0, 0, 32);
                        creature_evolution_card.setLayoutParams(layoutParams);
                        creature_evolution_card.setElevation(0f);
                        creature_evolution_card.setRadius(24f);
                        creature_evolution_card.setCardBackgroundColor(Color.MAGENTA);
                        creature_evolution_card.addView(generateCreatureEvolutionImage(creatureEvolutions.get(i).getImage_path()));
                        creature_evolution_card.addView(generateCreatureEvolutionName(creatureEvolutions.get(i).getName()));
                        creature_evolution_card.addView(generateCreatureEvolutionDescription(creatureEvolutions.get(i).getDescription()));
                        creature_evolution_layout.addView(creature_evolution_card);
                    }
                }

                if (creatureEvolutions.size() > 0) {
                    creatureEvolutions.clear();
                }
            }
        });

        return view;
    }

    private Observer<ShowUserCreature> showCreatureDetail = new Observer<ShowUserCreature>() {
        @Override
        public void onChanged(ShowUserCreature showUserCreature) {
            Glide.with(getActivity().getApplicationContext()).load(Const.IMG_URL + showUserCreature.getImage_path()).into(creature_detail_image);
            creature_detail_common_name.setText(showUserCreature.getCommon_name() + " (" + showUserCreature.getName() + ")");
            creature_detail_description.setText(showUserCreature.getDescription());
            creature_detail_domain.setText("Domain : " + showUserCreature.getGenus().getFamily().getOrder().getClassX().getPhylum().getKingdom().getDomain().getName());
            creature_detail_kingdom.setText("Kingdom : " + showUserCreature.getGenus().getFamily().getOrder().getClassX().getPhylum().getKingdom().getName());
            creature_detail_phylum.setText("Phylum : " + showUserCreature.getGenus().getFamily().getOrder().getClassX().getPhylum().getName());
            creature_detail_class.setText("Class : " + showUserCreature.getGenus().getFamily().getOrder().getClassX().getName());
            creature_detail_order.setText("Ordo : " + showUserCreature.getGenus().getFamily().getOrder().getName());
            creature_detail_family.setText("Family : " + showUserCreature.getGenus().getFamily().getName());
            creature_detail_genus.setText("Genus : " + showUserCreature.getGenus().getName());
            creature_detail_species.setText("Species : " + showUserCreature.getName());
        }
    };

    private ImageView generateCreatureEvolutionImage(String image_path) {
        ImageView imageView = new ImageView(getActivity().getApplicationContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(256, 256);
        layoutParams.setMargins(32, 0, 0, 8);
        imageView.setLayoutParams(layoutParams);
        Glide.with(getActivity().getApplicationContext()).load(Const.IMG_URL + image_path).into(imageView);

        return imageView;
    }

    private TextView generateCreatureEvolutionName(String name) {
        TextView textView = new TextView(getActivity().getApplicationContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(320, 24, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setText(name);
        textView.setTextSize(15f);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(getResources().getColor(R.color.dark_black));

        return textView;
    }

    private TextView generateCreatureEvolutionDescription(String description) {
        TextView textView = new TextView(getActivity().getApplicationContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(320, 80, 32, 0);
        textView.setLayoutParams(layoutParams);
        textView.setText(description.substring(0, 80) + "...");
        textView.setTextColor(getResources().getColor(R.color.dark_black));

        return textView;
    }
}
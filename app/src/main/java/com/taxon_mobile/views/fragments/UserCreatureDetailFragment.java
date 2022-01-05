package com.taxon_mobile.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taxon_mobile.R;
import com.taxon_mobile.helpers.Const;
import com.taxon_mobile.models.Creature;
import com.taxon_mobile.models.ShowUserCreature;
import com.taxon_mobile.models.UserCreature;
import com.taxon_mobile.viewmodels.SpeciesViewModel;
import com.taxon_mobile.views.MainActivity;

public class UserCreatureDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView creature_detail_image;
    private TextView creature_detail_common_name, creature_detail_name, creature_detail_description, creature_detail_genus, creature_detail_family, creature_detail_order, creature_detail_class, creature_detail_phylum, creature_detail_kingdom, creature_detail_domain;
    private SpeciesViewModel viewModel;
    private int speciesId;

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
        creature_detail_image = view.findViewById(R.id.creature_detail_image);
        creature_detail_common_name = view.findViewById(R.id.creature_detail_common_name);
        creature_detail_name = view.findViewById(R.id.creature_detail_name);
        creature_detail_description = view.findViewById(R.id.creature_detail_description);
        creature_detail_genus = view.findViewById(R.id.creature_detail_genus);
        creature_detail_family = view.findViewById(R.id.creature_detail_family);
        creature_detail_order = view.findViewById(R.id.creature_detail_order);
        creature_detail_class = view.findViewById(R.id.creature_detail_class);
        creature_detail_phylum = view.findViewById(R.id.creature_detail_phylum);
        creature_detail_kingdom = view.findViewById(R.id.creature_detail_kingdom);
        creature_detail_domain = view.findViewById(R.id.creature_detail_domain);
        viewModel = new ViewModelProvider(this).get(SpeciesViewModel.class);
        speciesId = getArguments().getInt("speciesId");
        viewModel.showCreature("Bearer " + MainActivity.token, String.valueOf(speciesId));
        viewModel.getShowCreatureDetail().observe(getViewLifecycleOwner(), showCreatureDetail);
        return view;
    }

    private Observer<ShowUserCreature> showCreatureDetail = new Observer<ShowUserCreature>() {
        @Override
        public void onChanged(ShowUserCreature showUserCreature) {
            Glide.with(getActivity().getApplicationContext()).load(Const.IMG_URL + showUserCreature.getImage_path()).into(creature_detail_image);
            creature_detail_common_name.setText(showUserCreature.getCommon_name());
            creature_detail_name.setText(showUserCreature.getName());
            creature_detail_description.setText(showUserCreature.getDescription());
            creature_detail_genus.setText(showUserCreature.getGenus().getName());
            creature_detail_family.setText(showUserCreature.getGenus().getFamily().getName());
            creature_detail_order.setText(showUserCreature.getGenus().getFamily().getOrder().getName());
            creature_detail_class.setText(showUserCreature.getGenus().getFamily().getOrder().getClassX().getName());
            creature_detail_phylum.setText(showUserCreature.getGenus().getFamily().getOrder().getClassX().getPhylum().getName());
            creature_detail_kingdom.setText(showUserCreature.getGenus().getFamily().getOrder().getClassX().getPhylum().getKingdom().getName());
            creature_detail_domain.setText(showUserCreature.getGenus().getFamily().getOrder().getClassX().getPhylum().getKingdom().getDomain().getName());
        }
    };
}
package com.taxon_mobile.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taxon_mobile.R;
import com.taxon_mobile.adapters.UserCreatureAdapter;
import com.taxon_mobile.models.Species;
import com.taxon_mobile.viewmodels.SpeciesViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserCreatureFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView user_creature_rv;
    private SpeciesViewModel viewModel;
    private List<Species> listUserCreature;

    public UserCreatureFragment() {
        // Required empty public constructor
    }

    public static UserCreatureFragment newInstance(String param1, String param2) {
        UserCreatureFragment fragment = new UserCreatureFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_creature, container, false);
        user_creature_rv = view.findViewById(R.id.user_creature_rv);
        listUserCreature = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(SpeciesViewModel.class);
        if (LoginFragment.token != "") {
            viewModel.userCreature("Bearer " + LoginFragment.token);
            viewModel.getUserCreatureDetail().observe(getViewLifecycleOwner(), showUserCreature);
        }

        return view;
    }

    private Observer<Species> showUserCreature = new Observer<Species>() {
        @Override
        public void onChanged(Species userCreature) {
            listUserCreature.add(userCreature);
            user_creature_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            UserCreatureAdapter adapter = new UserCreatureAdapter(getActivity().getApplicationContext());
            adapter.setListUserCreature(listUserCreature);
            user_creature_rv.setAdapter(adapter);
        }
    };
}
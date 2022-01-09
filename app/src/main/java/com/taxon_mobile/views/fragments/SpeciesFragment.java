package com.taxon_mobile.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.taxon_mobile.R;
import com.taxon_mobile.adapters.CreatureAdapter;
import com.taxon_mobile.models.Creature;
import com.taxon_mobile.viewmodels.SpeciesViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.List;

public class SpeciesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView species_rv;
    private SpeciesViewModel viewModel;
    public static Context context;

    public SpeciesFragment() {
        // Required empty public constructor
    }

    public static SpeciesFragment newInstance(String param1, String param2) {
        SpeciesFragment fragment = new SpeciesFragment();
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
        View view = inflater.inflate(R.layout.fragment_species, container, false);
        context = getActivity().getApplicationContext();
        species_rv = view.findViewById(R.id.species_rv);
        viewModel = new ViewModelProvider(this).get(SpeciesViewModel.class);
        viewModel.creature("Bearer " + MainActivity.token);
        viewModel.getCreatureDetail().observe(getViewLifecycleOwner(), showCreature);
        return view;
    }

    private Observer<List<Creature.Creatures>> showCreature = new Observer<List<Creature.Creatures>>() {
        @Override
        public void onChanged(List<Creature.Creatures> creatures) {
            species_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            CreatureAdapter adapter = new CreatureAdapter(getActivity().getApplicationContext());
            adapter.setListCreature(creatures);
            species_rv.setAdapter(adapter);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CreatureAdapter.listCreature.clear();
    }
}
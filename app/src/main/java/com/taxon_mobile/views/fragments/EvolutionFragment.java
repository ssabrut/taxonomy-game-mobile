package com.taxon_mobile.views.fragments;

import android.content.Context;
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
import com.taxon_mobile.adapters.EvolutionAdapter;
import com.taxon_mobile.models.Evolution;
import com.taxon_mobile.viewmodels.EvolutionViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.List;

public class EvolutionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView evolution_rv;
    private EvolutionViewModel viewModel;
    public static Context context;

    public EvolutionFragment() {
        // Required empty public constructor
    }

    public static EvolutionFragment newInstance(String param1, String param2) {
        EvolutionFragment fragment = new EvolutionFragment();
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
        View view = inflater.inflate(R.layout.fragment_evolution, container, false);
        context = getActivity().getApplicationContext();
        evolution_rv = view.findViewById(R.id.evolution_rv);
        viewModel = new ViewModelProvider(this).get(EvolutionViewModel.class);
        viewModel.evolution("Bearer " + MainActivity.token);
        viewModel.getEvolutionDetail().observe(getViewLifecycleOwner(), showEvolution);
        return view;
    }

    private Observer<List<Evolution.Evolutions>> showEvolution = new Observer<List<Evolution.Evolutions>>() {
        @Override
        public void onChanged(List<Evolution.Evolutions> evolutions) {
            evolution_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            EvolutionAdapter adapter = new EvolutionAdapter(getActivity().getApplicationContext());
            adapter.setListEvolution(evolutions);
            evolution_rv.setAdapter(adapter);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EvolutionAdapter.listEvolution.clear();
    }
}
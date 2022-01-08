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
import com.taxon_mobile.adapters.LeaderboardAdapter;
import com.taxon_mobile.models.Leaderboard;
import com.taxon_mobile.viewmodels.LeaderboardViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.List;

public class LeaderboardFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView leaderboard_rv;
    private LeaderboardViewModel viewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        leaderboard_rv = view.findViewById(R.id.leaderboard_rv);
        viewModel = new ViewModelProvider(this).get(LeaderboardViewModel.class);
        viewModel.leaderboard("Bearer " + MainActivity.token);
        viewModel.getLeaderboardDetail().observe(getViewLifecycleOwner(), showLeaderboard);
        return view;
    }

    private Observer<List<Leaderboard.Leaderboards>> showLeaderboard = new Observer<List<Leaderboard.Leaderboards>>() {
        @Override
        public void onChanged(List<Leaderboard.Leaderboards> leaderboards) {
            leaderboard_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            LeaderboardAdapter adapter = new LeaderboardAdapter(getActivity().getApplicationContext());
            adapter.setListLeaderboard(leaderboards);
            leaderboard_rv.setAdapter(adapter);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LeaderboardAdapter.listLeaderboard.clear();
    }
}
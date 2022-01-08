package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.Leaderboard;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardRepository {
    private static LeaderboardRepository repository;

    private LeaderboardRepository() {

    }

    public static LeaderboardRepository getInstance() {
        if (repository == null) {
            repository = new LeaderboardRepository();
        }

        return repository;
    }

    private List<Leaderboard.Leaderboards> leaderboardList = new ArrayList<>();
    public MutableLiveData<List<Leaderboard.Leaderboards>> getLeaderboard(String token) {
        final MutableLiveData<List<Leaderboard.Leaderboards>> result = new MutableLiveData<>();

        ApiService.endPoint().leaderboard(token).enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                leaderboardList.addAll(response.body().getLeaderboard());
                result.setValue(leaderboardList);
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {

            }
        });

        return result;
    }
}

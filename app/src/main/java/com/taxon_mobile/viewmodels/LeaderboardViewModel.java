package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.Leaderboard;
import com.taxon_mobile.repositories.LeaderboardRepository;

import java.util.List;

public class LeaderboardViewModel extends AndroidViewModel {
    private static LeaderboardRepository repository;

    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
        repository = LeaderboardRepository.getInstance();
    }

    private MutableLiveData<List<Leaderboard.Leaderboards>> resultLeaderboard = new MutableLiveData<>();

    public void leaderboard(String token) {
        resultLeaderboard = repository.getLeaderboard(token);
    }

    public LiveData<List<Leaderboard.Leaderboards>> getLeaderboardDetail() {
        return resultLeaderboard;
    }
}

package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.User;
import com.taxon_mobile.repositories.UserStatRepository;

public class UserStatViewModel extends AndroidViewModel {
    private UserStatRepository repository;

    public UserStatViewModel(@NonNull Application application) {
        super(application);
        repository = UserStatRepository.getInstance();
    }

    private MutableLiveData<User.Stat> resultUpgradePower = new MutableLiveData<>();

    public void upgradePower(String token) {
        resultUpgradePower = repository.upgradePower(token);
    }

    public LiveData<User.Stat> getUpgradePowerDetails() {
        return resultUpgradePower;
    }

    private MutableLiveData<User.Stat> resultSaveUserStat = new MutableLiveData<>();

    public void saveUserStat(String token, int power, int evo, int dna, int point) {
        resultSaveUserStat = repository.saveUserStat(token, power, evo, dna, point);
    }
}

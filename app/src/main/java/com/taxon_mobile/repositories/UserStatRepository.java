package com.taxon_mobile.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserStatRepository {
    private static UserStatRepository repository;

    private UserStatRepository() {
    }

    public static UserStatRepository getInstance() {
        if (repository == null) {
            repository = new UserStatRepository();
        }

        return repository;
    }

    public MutableLiveData<User.Stat> upgradePower(String token) {
        final MutableLiveData<User.Stat> result = new MutableLiveData<>();

        ApiService.endPoint().upgradePower(token).enqueue(new Callback<User.Stat>() {
            @Override
            public void onResponse(Call<User.Stat> call, Response<User.Stat> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User.Stat> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<User.Stat> saveUserStat(String token, int power, int evo, int dna, int point) {
        final MutableLiveData<User.Stat> result = new MutableLiveData<>();

        ApiService.endPoint().saveUserStat(token, power, evo, dna, point).enqueue(new Callback<User.Stat>() {
            @Override
            public void onResponse(Call<User.Stat> call, Response<User.Stat> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User.Stat> call, Throwable t) {

            }
        });

        return result;
    }
}

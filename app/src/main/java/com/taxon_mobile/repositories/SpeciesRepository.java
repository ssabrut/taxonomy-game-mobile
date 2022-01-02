package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.UserCreature;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeciesRepository {
    private static SpeciesRepository repository;

    private SpeciesRepository() {
    }

    public static SpeciesRepository getInstance() {
        if (repository == null) {
            repository = new SpeciesRepository();
        }

        return repository;
    }

    private List<UserCreature.Species> userCreatureList = new ArrayList<>();
    public MutableLiveData<List<UserCreature.Species>> getUserCreature(String token) {
        final MutableLiveData<List<UserCreature.Species>> result = new MutableLiveData<>();

        ApiService.endPoint().userCreatures(token).enqueue(new Callback<UserCreature>() {
            @Override
            public void onResponse(Call<UserCreature> call, Response<UserCreature> response) {
                userCreatureList.addAll(response.body().getSpecies());
                result.setValue(userCreatureList);
            }

            @Override
            public void onFailure(Call<UserCreature> call, Throwable t) {

            }
        });

        return result;
    }
}

package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.Creature;
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
                t.printStackTrace();
            }
        });

        return result;
    }

    private List<Creature.Creatures> creatureList = new ArrayList<Creature.Creatures>();
    public MutableLiveData<List<Creature.Creatures>> getCreatures(String token) {
        final MutableLiveData<List<Creature.Creatures>> result = new MutableLiveData<>();

        ApiService.endPoint().creatures(token).enqueue(new Callback<Creature>() {
            @Override
            public void onResponse(Call<Creature> call, Response<Creature> response) {
                creatureList.addAll(response.body().getCreatures());
                result.setValue(creatureList);
            }

            @Override
            public void onFailure(Call<Creature> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return result;
    }
}

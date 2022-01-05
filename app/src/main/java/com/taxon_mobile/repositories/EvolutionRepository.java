package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.Evolution;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvolutionRepository {
    private static EvolutionRepository repository;

    private EvolutionRepository() {

    }

    public static EvolutionRepository getInstance() {
        if (repository == null) {
            repository = new EvolutionRepository();
        }

        return repository;
    }

    private List<Evolution.Evolutions> evolutionList = new ArrayList<>();
    public MutableLiveData<List<Evolution.Evolutions>> getEvolution(String token) {
        final MutableLiveData<List<Evolution.Evolutions>> result = new MutableLiveData<>();

        ApiService.endPoint().evolution(token).enqueue(new Callback<Evolution>() {
            @Override
            public void onResponse(Call<Evolution> call, Response<Evolution> response) {
                evolutionList.addAll(response.body().getEvolutions());
                result.setValue(evolutionList);
            }

            @Override
            public void onFailure(Call<Evolution> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return result;
    }
}

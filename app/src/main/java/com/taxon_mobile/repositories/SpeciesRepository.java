package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.Species;

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

    public MutableLiveData<Species> getUserCreature(String token) {
        final MutableLiveData<Species> result = new MutableLiveData<>();

        ApiService.endPoint().userCreatures(token).enqueue(new Callback<Species>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                result.setValue(response.body());
                System.out.println("response: " + response.body().getUserCreatures().get(0).getCommon_name());
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {

            }
        });

        System.out.println(result.getValue());
        return result;
    }
}

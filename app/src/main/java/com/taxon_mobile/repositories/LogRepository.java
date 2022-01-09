package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogRepository {
    private static LogRepository repository;

    private LogRepository() {

    }

    public static LogRepository getInstance() {
        if (repository == null) {
            repository = new LogRepository();
        }

        return repository;
    }

    public MutableLiveData<Log> setLog(String token, String model, String description) {
        final MutableLiveData<Log> result = new MutableLiveData<>();

        ApiService.endPoint().log(token, model, description).enqueue(new Callback<Log>() {
            @Override
            public void onResponse(Call<Log> call, Response<Log> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Log> call, Throwable t) {

            }
        });

        return result;
    }
}

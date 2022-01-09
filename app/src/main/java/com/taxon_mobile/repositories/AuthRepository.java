package com.taxon_mobile.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.LogoutResponse;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.views.activities.LoginActivity;
import com.taxon_mobile.views.activities.RegisterAccountActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static AuthRepository repository;

    private AuthRepository() {

    }

    public static AuthRepository getInstance() {
        if (repository == null) {
            repository = new AuthRepository();
        }

        return repository;
    }

    public MutableLiveData<LoginResponse> login(String email, String password) {
        final MutableLiveData<LoginResponse> result = new MutableLiveData<>();

        ApiService.endPoint().login(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.context, jsonObject.getString("status_message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return result;
    }

    public MutableLiveData<RegisterResponse> register(String email, String password, String username, String name, String school, String city, String birthyear) {
        final MutableLiveData<RegisterResponse> result = new MutableLiveData<>();

        ApiService.endPoint().register(email, password, username, name, school, city, birthyear).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(RegisterAccountActivity.context, jsonObject.getString("status_message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                t.printStackTrace();
            }

        });

        return result;
    }

    public MutableLiveData<LogoutResponse> logout(String token) {
        final MutableLiveData<LogoutResponse> result = new MutableLiveData<>();

        ApiService.endPoint().logout(token).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {

            }
        });

        return result;
    }
}

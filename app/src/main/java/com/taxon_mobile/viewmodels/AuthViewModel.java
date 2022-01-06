package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.LogoutResponse;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.repositories.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository repository;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = AuthRepository.getInstance();
    }

    private MutableLiveData<LoginResponse> resultLogin = new MutableLiveData<>();
    public void login(String email, String password) {
        resultLogin = repository.login(email, password);
    }

    public LiveData<LoginResponse> getLoginDetails() {
        return resultLogin;
    }

    private MutableLiveData<RegisterResponse> resultRegister = new MutableLiveData<>();
    public void register(String name, String username, String email, String password){
        resultRegister = repository.register(name, username, email, password);
    }

    public LiveData<RegisterResponse> getRegisterDetails() { return resultRegister; }

    private MutableLiveData<LogoutResponse> resultLogout = new MutableLiveData<>();
    public void logout(String token) {
        resultLogout = repository.logout(token);
    }

    public LiveData<LogoutResponse> getLogoutDetail() {
        return resultLogout;
    }
}

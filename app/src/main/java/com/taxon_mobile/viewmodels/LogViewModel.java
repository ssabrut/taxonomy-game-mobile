package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.taxon_mobile.repositories.LogRepository;

public class LogViewModel extends AndroidViewModel {
    private static LogRepository repository;

    public LogViewModel(@NonNull Application application) {
        super(application);
        repository = LogRepository.getInstance();
    }

    public static void log(String token, String model, String description) {
        repository.setLog(token, model, description);
    }
}

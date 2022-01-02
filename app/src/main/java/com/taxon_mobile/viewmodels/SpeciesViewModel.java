package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.Species;
import com.taxon_mobile.repositories.SpeciesRepository;

public class SpeciesViewModel extends AndroidViewModel {
    private static SpeciesRepository repository;

    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repository = SpeciesRepository.getInstance();
    }

    private MutableLiveData<Species> resultUserCreature = new MutableLiveData<>();

    public void userCreature(String token) {
        resultUserCreature = repository.getUserCreature(token);
    }

    public LiveData<Species> getUserCreatureDetail() {
        return resultUserCreature;
    }
}

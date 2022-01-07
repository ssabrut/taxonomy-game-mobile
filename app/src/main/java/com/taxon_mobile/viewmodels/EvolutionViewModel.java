package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.Evolution;
import com.taxon_mobile.repositories.EvolutionRepository;

import java.util.List;

public class EvolutionViewModel extends AndroidViewModel {
    private static EvolutionRepository repository;

    public EvolutionViewModel(@NonNull Application application) {
        super(application);
        repository = EvolutionRepository.getInstance();
    }

    private MutableLiveData<List<Evolution.Evolutions>> resultEvolution = new MutableLiveData<>();

    public void evolution(String token) {
        resultEvolution = repository.getEvolution(token);
    }

    public LiveData<List<Evolution.Evolutions>> getEvolutionDetail() {
        return resultEvolution;
    }

    public static void unlockEvolution(String token, int evolutionId) {
        repository.unlockEvolution(token, evolutionId);
    }
}

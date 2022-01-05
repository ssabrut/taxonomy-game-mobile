package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.Creature;
import com.taxon_mobile.models.UserCreature;
import com.taxon_mobile.repositories.SpeciesRepository;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private static SpeciesRepository repository;

    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repository = SpeciesRepository.getInstance();
    }

    private MutableLiveData<List<UserCreature.Species>> resultUserCreature = new MutableLiveData<>();

    public void userCreature(String token) {
        resultUserCreature = repository.getUserCreature(token);
    }

    public LiveData<List<UserCreature.Species>> getUserCreatureDetail() {
        return resultUserCreature;
    }

    private MutableLiveData<List<Creature.Creatures>> resultCreature = new MutableLiveData<>();

    public void creature(String token) {
        resultCreature = repository.getCreatures(token);
    }

    public LiveData<List<Creature.Creatures>> getCreatureDetail() {
        return resultCreature;
    }

    public static void unlockCreature(String token, int speciesId) {
        repository.getUnlockCreature(token, speciesId);
    }
}

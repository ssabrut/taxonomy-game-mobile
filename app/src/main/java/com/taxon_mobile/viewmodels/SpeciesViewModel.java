package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.Creature;
import com.taxon_mobile.models.ShowUserCreature;
import com.taxon_mobile.models.UserCreature;
import com.taxon_mobile.repositories.SpeciesRepository;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private static SpeciesRepository repository;

    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repository = SpeciesRepository.getInstance();
    }

    private MutableLiveData<List<UserCreature.UserCreatures>> resultUserCreature = new MutableLiveData<>();

    public void userCreature(String token) {
        resultUserCreature = repository.getUserCreature(token);
    }

    public LiveData<List<UserCreature.UserCreatures>> getUserCreatureDetail() {
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

    private MutableLiveData<ShowUserCreature> resultShowCreature = new MutableLiveData<>();

    public void showCreature(String token, String speciesId) {
        resultShowCreature = repository.showCreature(token, speciesId);
    }

    public LiveData<ShowUserCreature> getShowCreatureDetail() {
        return resultShowCreature;
    }
}

package com.taxon_mobile.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.models.Quiz;
import com.taxon_mobile.repositories.QuizRepository;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    private static QuizRepository repository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        repository = QuizRepository.getInstance();
    }

    private MutableLiveData<List<Quiz.Quizzes>> resultQuiz = new MutableLiveData<>();

    public void quiz(String token) {
        resultQuiz = repository.getQuiz(token);
    }

    public LiveData<List<Quiz.Quizzes>> getQuizDetail() {
        return resultQuiz;
    }
}

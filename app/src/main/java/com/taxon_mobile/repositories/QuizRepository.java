package com.taxon_mobile.repositories;

import androidx.lifecycle.MutableLiveData;

import com.taxon_mobile.api.ApiService;
import com.taxon_mobile.models.Quiz;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    private static QuizRepository repository;

    private QuizRepository() {

    }

    public static QuizRepository getInstance() {
        if (repository == null) {
            repository = new QuizRepository();
        }

        return repository;
    }

    private List<Quiz.Quizzes> quizList = new ArrayList<>();
    public MutableLiveData<List<Quiz.Quizzes>> getQuiz(String token) {
        final MutableLiveData<List<Quiz.Quizzes>> result = new MutableLiveData<>();

        ApiService.endPoint().quiz(token).enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                quizList.addAll(response.body().getQuizzes());
                result.setValue(quizList);
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {

            }
        });

        return result;
    }
}

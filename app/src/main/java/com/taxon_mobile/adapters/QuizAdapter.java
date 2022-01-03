package com.taxon_mobile.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.models.Quiz;
import com.taxon_mobile.views.fragments.QuizFragment;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private Context context;
    public static List<Quiz.Quizzes> listQuiz;

    public QuizAdapter(Context context) {
        this.context = context;
    }

    private List<Quiz.Quizzes> getListQuiz() {
        return listQuiz;
    }

    public void setListQuiz(List<Quiz.Quizzes> listQuiz) {
        this.listQuiz = listQuiz;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_quiz, parent, false);
        return new QuizAdapter.QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        final Quiz.Quizzes result = getListQuiz().get(position);
        holder.quiz_question.setText(result.getQuestion());
        holder.quiz_key.setText(result.getAnswer());
    }

    @Override
    public int getItemCount() {
        return getListQuiz().size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView quiz_question, quiz_key;
        TextInputLayout quiz_answer;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            quiz_question = itemView.findViewById(R.id.quiz_question);
            quiz_answer = itemView.findViewById(R.id.quiz_answer);
            quiz_key = itemView.findViewById(R.id.quiz_key);
        }
    }
}

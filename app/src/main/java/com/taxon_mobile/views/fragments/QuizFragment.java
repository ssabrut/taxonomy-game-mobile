package com.taxon_mobile.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.adapters.QuizAdapter;
import com.taxon_mobile.models.Quiz;
import com.taxon_mobile.viewmodels.QuizViewModel;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private QuizViewModel viewModel;
    public static Button quiz_submit_btn;
    private RecyclerView quiz_rv;

    private List<String> answers;
    private List<String> keys;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        QuizAdapter adapter = new QuizAdapter(getActivity().getApplicationContext());
        quiz_rv = view.findViewById(R.id.quiz_rv);
        quiz_submit_btn = view.findViewById(R.id.quiz_submit_btn);
        answers = new ArrayList<>();
        keys = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        viewModel.quiz("Bearer " + MainActivity.token);
        viewModel.getQuizDetail().observe(getViewLifecycleOwner(), showQuiz);

        quiz_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answers.size() > 0) {
                    answers.clear();
                }

                if (keys.size() > 0) {
                    keys.clear();
                }

                for (int i = 0; i < adapter.getItemCount(); i++) {
                    View view1 = quiz_rv.getChildAt(i);
                    TextInputLayout text = view1.findViewById(R.id.quiz_answer);
                    TextView key = view1.findViewById(R.id.quiz_key);
                    String answer = text.getEditText().getText().toString();
                    String answerKey = key.getText().toString();
                    answers.add(answer);
                    keys.add(answerKey);
                }

                for (int i = 0; i < keys.size(); i++) {
                    if (answers.get(i).equalsIgnoreCase(keys.get(i))) {
                        MainActivity.point += 10;
                    }
                }

                UserStatViewModel statViewModel = new ViewModelProvider(getActivity()).get(UserStatViewModel.class);
                statViewModel.saveUserStat("Bearer " + MainActivity.token, MainActivity.power, MainActivity.evo, MainActivity.dna, MainActivity.point);
                SharedPreferences sp = getContext().getSharedPreferences("UserStat", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("power", MainActivity.power);
                editor.putInt("evo", MainActivity.evo);
                editor.putInt("dna", MainActivity.dna);
                editor.putInt("point", MainActivity.point);
                editor.commit();
                Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_mainFragment);
            }
        });

        return view;
    }

    private Observer<List<Quiz.Quizzes>> showQuiz = new Observer<List<Quiz.Quizzes>>() {
        @Override
        public void onChanged(List<Quiz.Quizzes> quizzes) {
            quiz_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            QuizAdapter adapter = new QuizAdapter(getActivity().getApplicationContext());
            adapter.setListQuiz(quizzes);
            quiz_rv.setAdapter(adapter);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        QuizAdapter.listQuiz.clear();
    }
}
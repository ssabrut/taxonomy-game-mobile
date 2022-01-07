package com.taxon_mobile.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.taxon_mobile.R;
import com.taxon_mobile.adapters.QuizAdapter;
import com.taxon_mobile.helpers.IOnBackPressed;
import com.taxon_mobile.models.Quiz;
import com.taxon_mobile.viewmodels.QuizViewModel;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment implements IOnBackPressed {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private QuizViewModel viewModel;
    public static Button quiz_submit_btn;
    private RecyclerView quiz_rv;
    private Dialog quizFeedbackDialog;
    private LinearLayout quiz_feedback_layout;
    private Button quiz_feedback_finish_btn;
    private View foo;

    private List<String> answers;
    private List<String> keys;
    private List<String> questions;
    private int correctAnswer = 0;

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
        foo = view;
        quizFeedbackDialog = new Dialog(getActivity());
        quiz_rv = view.findViewById(R.id.quiz_rv);
        quiz_submit_btn = view.findViewById(R.id.quiz_submit_btn);
        answers = new ArrayList<>();
        keys = new ArrayList<>();
        questions = new ArrayList<>();
        MainActivity.counter = 0;
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        viewModel.quiz("Bearer " + MainActivity.token);
        viewModel.getQuizDetail().observe(getViewLifecycleOwner(), showQuiz);
        quizFeedbackDialog();

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
                        correctAnswer++;
                    } else {
                        TextView textView = new TextView(getActivity().getApplicationContext());
                        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );

                        if (i == 0) {
                            layoutParams.setMargins(32, 64, 32, 0);
                        } else {
                            layoutParams.setMargins(32, 16, 32, 0);
                        }

                        textView.setLayoutParams(layoutParams);
                        textView.setText(questions.get(i));
                        textView.setTextColor(getResources().getColor(R.color.dark_black));
                        quiz_feedback_layout.addView(textView);
                    }
                }

                TextView quiz_feedback_finish_btn = new TextView(getActivity().getApplicationContext());
                quiz_feedback_finish_btn.setText(getResources().getText(R.string.finish_quiz));
                quiz_feedback_finish_btn.setGravity(Gravity.END | Gravity.BOTTOM);
                quiz_feedback_finish_btn.setTextSize(20f);
                quiz_feedback_finish_btn.setPadding(0, 32, 65, 56);
                quiz_feedback_layout.addView(quiz_feedback_finish_btn);

                quiz_feedback_finish_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UserStatViewModel statViewModel = new ViewModelProvider(getActivity()).get(UserStatViewModel.class);
                        statViewModel.saveUserStat("Bearer " + MainActivity.token, MainActivity.power, MainActivity.evo, MainActivity.dna, MainActivity.point);
                        SharedPreferences sp = getContext().getSharedPreferences("UserStat", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("power", MainActivity.power);
                        editor.putInt("evo", MainActivity.evo);
                        editor.putInt("dna", MainActivity.dna);
                        editor.putInt("point", MainActivity.point);
                        editor.commit();
                        Navigation.findNavController(foo).navigate(R.id.action_quizFragment_to_mainFragment);
                        quizFeedbackDialog.dismiss();
                    }
                });

                if (questions.size() > 0) {
                    questions.clear();
                }

                quizFeedbackDialog.show();
            }
        });

        return view;
    }

    private Observer<List<Quiz.Quizzes>> showQuiz = new Observer<List<Quiz.Quizzes>>() {
        @Override
        public void onChanged(List<Quiz.Quizzes> quizzes) {
            quiz_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            for (int i = 0; i < quizzes.size(); i++) {
                questions.add(quizzes.get(i).getQuestion());
            }

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


    @Override
    public boolean onBackPresed() {
        return true;
    }

    private void quizFeedbackDialog() {
        quizFeedbackDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        quizFeedbackDialog.setContentView(R.layout.dialog_quiz_feedback);
        quizFeedbackDialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        quizFeedbackDialog.setCancelable(false);
        quiz_feedback_layout = quizFeedbackDialog.findViewById(R.id.quiz_feedback_layout);
    }
}
package com.taxon_mobile.views.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.taxon_mobile.viewmodels.LogViewModel;
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
    private Dialog quizFeedbackDialog;
    private LinearLayout quiz_feedback_layout;
    private Button quiz_feedback_finish_btn;
    private View foo;
    private TextView feedback_title, quiz_question1, quiz_question2, quiz_question3, quiz_question4, quiz_question5, quiz_question6, quiz_question7, quiz_question8, quiz_question9, quiz_question10, quiz_key1, quiz_key2, quiz_key3, quiz_key4, quiz_key5, quiz_key6, quiz_key7, quiz_key8, quiz_key9, quiz_key10;
    private TextInputLayout quiz_answer1, quiz_answer2, quiz_answer3, quiz_answer4, quiz_answer5, quiz_answer6, quiz_answer7, quiz_answer8, quiz_answer9, quiz_answer10;

    private List<String> answers;
    private List<String> keys;
    public static List<String> questions;
    private int tempPoin = 0;

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
        quiz_submit_btn = view.findViewById(R.id.quiz_submit_btn);
        answers = new ArrayList<>();
        keys = new ArrayList<>();
        questions = new ArrayList<>();
        quiz_question1 = view.findViewById(R.id.quiz_question1);
        quiz_question2 = view.findViewById(R.id.quiz_question2);
        quiz_question3 = view.findViewById(R.id.quiz_question3);
        quiz_question4 = view.findViewById(R.id.quiz_question4);
        quiz_question5 = view.findViewById(R.id.quiz_question5);
        quiz_question6 = view.findViewById(R.id.quiz_question6);
        quiz_question7 = view.findViewById(R.id.quiz_question7);
        quiz_question8 = view.findViewById(R.id.quiz_question8);
        quiz_question9 = view.findViewById(R.id.quiz_question9);
        quiz_question10 = view.findViewById(R.id.quiz_question10);

        quiz_answer1 = view.findViewById(R.id.quiz_answer1);
        quiz_answer2 = view.findViewById(R.id.quiz_answer2);
        quiz_answer3 = view.findViewById(R.id.quiz_answer3);
        quiz_answer4 = view.findViewById(R.id.quiz_answer4);
        quiz_answer5 = view.findViewById(R.id.quiz_answer5);
        quiz_answer6 = view.findViewById(R.id.quiz_answer6);
        quiz_answer7 = view.findViewById(R.id.quiz_answer7);
        quiz_answer8 = view.findViewById(R.id.quiz_answer8);
        quiz_answer9 = view.findViewById(R.id.quiz_answer9);
        quiz_answer10 = view.findViewById(R.id.quiz_answer10);

        quiz_key1 = view.findViewById(R.id.quiz_key1);
        quiz_key2 = view.findViewById(R.id.quiz_key2);
        quiz_key3 = view.findViewById(R.id.quiz_key3);
        quiz_key4 = view.findViewById(R.id.quiz_key4);
        quiz_key5 = view.findViewById(R.id.quiz_key5);
        quiz_key6 = view.findViewById(R.id.quiz_key6);
        quiz_key7 = view.findViewById(R.id.quiz_key7);
        quiz_key8 = view.findViewById(R.id.quiz_key8);
        quiz_key9 = view.findViewById(R.id.quiz_key9);
        quiz_key10 = view.findViewById(R.id.quiz_key10);

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        viewModel.quiz("Bearer " + MainActivity.token);
        viewModel.getQuizDetail().observe(getViewLifecycleOwner(), new Observer<List<Quiz.Quizzes>>() {
            @Override
            public void onChanged(List<Quiz.Quizzes> quizzes) {
                for (int i = 0; i < quizzes.size(); i++) {
                    questions.add(quizzes.get(i).getQuestion());
                    keys.add(quizzes.get(i).getAnswer());
                }

                String question1 = questions.get(0);
                String question2 = questions.get(1);
                String question3 = questions.get(2);
                String question4 = questions.get(3);
                String question5 = questions.get(4);
                String question6 = questions.get(5);
                String question7 = questions.get(6);
                String question8 = questions.get(7);
                String question9 = questions.get(8);
                String question10 = questions.get(9);

                String key1 = keys.get(0);
                String key2 = keys.get(1);
                String key3 = keys.get(2);
                String key4 = keys.get(3);
                String key5 = keys.get(4);
                String key6 = keys.get(5);
                String key7 = keys.get(6);
                String key8 = keys.get(7);
                String key9 = keys.get(8);
                String key10 = keys.get(9);

                quiz_question1.setText(question1);
                quiz_question2.setText(question2);
                quiz_question3.setText(question3);
                quiz_question4.setText(question4);
                quiz_question5.setText(question5);
                quiz_question6.setText(question6);
                quiz_question7.setText(question7);
                quiz_question8.setText(question8);
                quiz_question9.setText(question9);
                quiz_question10.setText(question10);

                quiz_key1.setText(key1);
                quiz_key2.setText(key2);
                quiz_key3.setText(key3);
                quiz_key4.setText(key4);
                quiz_key5.setText(key5);
                quiz_key6.setText(key6);
                quiz_key7.setText(key7);
                quiz_key8.setText(key8);
                quiz_key9.setText(key9);
                quiz_key10.setText(key10);
            }
        });

        quizFeedbackDialog();

        TextView quiz_feedback_finish_btn = new TextView(getActivity().getApplicationContext());
        quiz_feedback_finish_btn.setText(getResources().getText(R.string.finish_quiz));
        quiz_feedback_finish_btn.setGravity(Gravity.END | Gravity.BOTTOM);
        quiz_feedback_finish_btn.setTextSize(20f);
        quiz_feedback_finish_btn.setPadding(0, 32, 65, 56);
        quiz_feedback_finish_btn.setTextColor(getResources().getColor(R.color.dark_black));
        quiz_feedback_layout.addView(quiz_feedback_finish_btn);
        quiz_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer1 = quiz_answer1.getEditText().getText().toString().trim();
                String answer2 = quiz_answer2.getEditText().getText().toString().trim();
                String answer3 = quiz_answer3.getEditText().getText().toString().trim();
                String answer4 = quiz_answer4.getEditText().getText().toString().trim();
                String answer5 = quiz_answer5.getEditText().getText().toString().trim();
                String answer6 = quiz_answer6.getEditText().getText().toString().trim();
                String answer7 = quiz_answer7.getEditText().getText().toString().trim();
                String answer8 = quiz_answer8.getEditText().getText().toString().trim();
                String answer9 = quiz_answer9.getEditText().getText().toString().trim();
                String answer10 = quiz_answer10.getEditText().getText().toString().trim();

                String key1 = quiz_key1.getText().toString().trim();
                String key2 = quiz_key2.getText().toString().trim();
                String key3 = quiz_key3.getText().toString().trim();
                String key4 = quiz_key4.getText().toString().trim();
                String key5 = quiz_key5.getText().toString().trim();
                String key6 = quiz_key6.getText().toString().trim();
                String key7 = quiz_key7.getText().toString().trim();
                String key8 = quiz_key8.getText().toString().trim();
                String key9 = quiz_key9.getText().toString().trim();
                String key10 = quiz_key10.getText().toString().trim();

                if (answer1.equalsIgnoreCase(key1)
                        && answer2.equalsIgnoreCase(key2)
                        && answer3.equalsIgnoreCase(key3)
                        && answer4.equalsIgnoreCase(key4)
                        && answer5.equalsIgnoreCase(key5)
                        && answer6.equalsIgnoreCase(key6)
                        && answer7.equalsIgnoreCase(key7)
                        && answer8.equalsIgnoreCase(key8)
                        && answer9.equalsIgnoreCase(key9)
                        && answer10.equalsIgnoreCase(key10)) {
                    feedback_title.setText("Selamat anda telah membuka bioma baru");
                    feedback_title.setTextSize(18f);
                    feedback_title.setTextColor(getResources().getColor(R.color.dark_black));
                    tempPoin = MainActivity.point;
                    tempPoin = MainActivity.point;
                    MainActivity.point += 100;
                } else {
                    System.out.println("ada yagn salah");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Apakah kamu yakin?");
                builder.setMessage("Jika kamu sudah yakin tekan tombol kirim atau tekan tombol kembali untuk mengecek jawaban kembali!");
                builder.setNegativeButton(R.string.cancel_quiz_finish, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.point = tempPoin;
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton(R.string.confirmation_quiz_finish, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        quizFeedbackDialog.show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

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
                        if (MainActivity.point >= 100) {
                            LogViewModel.log("Bearer " + MainActivity.token, "Quiz", "User id : " + MainActivity.user.getId() + " finished quiz with score " + MainActivity.point);
                            LogViewModel.log("Bearer " + MainActivity.token, "Quiz", "User id : " + MainActivity.user.getId() + " unlocked sea biome");
                        } else {
                            LogViewModel.log("Bearer " + MainActivity.token, "Quiz", "User id : " + MainActivity.user.getId() + " finished quiz with score " + MainActivity.point);
                        }

                        Navigation.findNavController(foo).navigate(R.id.action_quizFragment_to_mainFragment);
                        quizFeedbackDialog.dismiss();
                    }
                });

//                for (int i = 0; i < adapter.getItemCount(); i++) {
//                    final RecyclerView.ViewHolder holder = quiz_rv.getChildViewHolder(quiz_rv.getChildAt(i));
//                    TextInputLayout text = holder.itemView.findViewById(R.id.quiz_answer);
//                    TextView key = holder.itemView.findViewById(R.id.quiz_key);
//                    String answer = text.getEditText().getText().toString();
//                    String answerKey = key.getText().toString();
//                    answers.add(answer);
//                    keys.add(answerKey);
//                }
//
//                if (answers.size() > 0) {
//                    answers.clear();
//                }
//
//                if (keys.size() > 0) {
//                    keys.clear();
//                }
//
//                for (int i = 0; i < keys.size(); i++) {
//                    if (answers.get(i).equalsIgnoreCase(keys.get(i))) {
//                        correctAnswer++;
//                    } else {
//                        TextView textView = new TextView(getActivity().getApplicationContext());
//                        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
//                                ViewGroup.LayoutParams.WRAP_CONTENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT
//                        );
//
//                        if (i == 0) {
//                            layoutParams.setMargins(48, 48, 48, 0);
//                        } else {
//                            layoutParams.setMargins(48, 16, 48, 0);
//                        }
//
//                        textView.setLayoutParams(layoutParams);
//                        textView.setText(questions.get(i));
//                        textView.setTextColor(getResources().getColor(R.color.dark_black));
//
//                        quiz_feedback_layout.addView(textView);
//                    }
//
//                    if (correctAnswer < keys.size()) {
//                        feedback_title.setText("Ada yang salah, coba di baca lagi");
//                        feedback_title.setTextSize(18f);
//                        feedback_title.setTextColor(getResources().getColor(R.color.dark_black));
//                    } else if (correctAnswer == keys.size()) {
//                        feedback_title.setText("Selamat anda telah membuka bioma baru");
//                        feedback_title.setTextSize(18f);
//                        feedback_title.setTextColor(getResources().getColor(R.color.dark_black));
//                        tempPoin = MainActivity.point;
//                        MainActivity.point += 100;
//                    }
//                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        questions.clear();
        keys.clear();
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
        feedback_title = quizFeedbackDialog.findViewById(R.id.feedback_title);
        LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        linearLayout.setMargins(48, 48, 48, 0);
        feedback_title.setLayoutParams(linearLayout);
    }
}
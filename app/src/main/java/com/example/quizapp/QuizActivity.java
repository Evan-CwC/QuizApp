package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvWelcome, tvQuestion;
    private ProgressBar progressBar;
    private RadioGroup rgOptions;
    private Button btnSubmit;

    private List<Question> questionList;
    private int currentIndex = 0;
    private int score = 0;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvWelcome = findViewById(R.id.tv_welcome);
        tvQuestion = findViewById(R.id.tv_question);
        progressBar = findViewById(R.id.progressBar);
        rgOptions = findViewById(R.id.rg_options);
        btnSubmit = findViewById(R.id.btn_submit);

        userName = getIntent().getStringExtra("username");
        tvWelcome.setText("Welcome " + userName + "!");

        questionList = getSampleQuestions();
        loadQuestion();

        btnSubmit.setOnClickListener(v -> checkAnswer());
    }

    private void loadQuestion() {
        rgOptions.removeAllViews();
        Question q = questionList.get(currentIndex);
        tvQuestion.setText(q.getQuestionText());

        for (String option : q.getOptions()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(option);
            rb.setTextColor(Color.BLACK);
            rb.setBackground(ContextCompat.getDrawable(this, R.drawable.option_border));
            rb.setPadding(20, 20, 20, 20);
            rb.setLayoutParams(new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
            ));

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rb.getLayoutParams();
            params.setMargins(0, 0, 0, 24); // left, top, right, bottom
            rb.setLayoutParams(params);

            rgOptions.addView(rb);
        }


        updateProgress();
    }


    private void checkAnswer() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedOption = findViewById(selectedId);
        String selectedText = selectedOption.getText().toString();
        String correctAnswer = questionList.get(currentIndex).getCorrectAnswer();

        for (int i = 0; i < rgOptions.getChildCount(); i++) {
            rgOptions.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
        }

        for (int i = 0; i < rgOptions.getChildCount(); i++) {
            RadioButton option = (RadioButton) rgOptions.getChildAt(i);
            if (option.getText().toString().equals(correctAnswer)) {
                option.setBackgroundColor(ContextCompat.getColor(this, R.color.soft_sky_green));
            } else if (option.getId() == selectedId) {
                option.setBackgroundColor(ContextCompat.getColor(this, R.color.soft_sky_red));
            }
            option.setEnabled(false);
        }

        if (selectedText.equals(correctAnswer)) {
            score++;
        }

        btnSubmit.postDelayed(() -> {
            currentIndex++;
            if (currentIndex < questionList.size()) {
                loadQuestion();
            } else {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", questionList.size());
                intent.putExtra("username", userName);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    private void updateProgress() {
        int progress = (int) (((double) (currentIndex + 1) / questionList.size()) * 100);
        progressBar.setProgress(progress);
    }

    private List<Question> getSampleQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question("What is Android?", new String[]{"OS", "Browser", "Game"}, "OS"));
        list.add(new Question("Which company created Android?", new String[]{"Apple", "Google", "Microsoft"}, "Google"));
        list.add(new Question("What is Kotlin?", new String[]{"Framework", "Language", "IDE"}, "Language"));
        list.add(new Question("Which layout arranges views vertically or horizontally?", new String[]{"LinearLayout", "ConstraintLayout", "RelativeLayout"}, "LinearLayout"));
        list.add(new Question("Which file contains the app's UI layouts?", new String[]{"MainActivity.java", "AndroidManifest.xml", "XML layout files"}, "XML layout files"));

        return list;
    }

}

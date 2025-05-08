package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore;
    private Button btnRetry, btnFinish;
    private String username;
    private int score, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tv_score);
        btnRetry = findViewById(R.id.btn_retry);
        btnFinish = findViewById(R.id.btn_finish);

        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 0);
        username = getIntent().getStringExtra("username");

        tvScore.setText("Your Score: " + score + " / " + total);

        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });

        btnFinish.setOnClickListener(v -> finishAffinity());
    }
}

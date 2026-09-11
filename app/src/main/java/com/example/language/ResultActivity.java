package com.example.language;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView valueText;
    ImageView star;
    Button retryBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        valueText = findViewById(R.id.value);
        star = findViewById(R.id.star);
        retryBtn = findViewById(R.id.retryBtn);
        homeBtn = findViewById(R.id.homeBtn);


        int score = getIntent().getIntExtra("score", 0);
        valueText.setText(String.valueOf(score));


        if (score <= 2) {
            valueText.setTextColor(Color.parseColor("#E74C3C"));
        } else if (score == 3) {
            valueText.setTextColor(Color.parseColor("#F39C12"));
        } else {
            valueText.setTextColor(Color.parseColor("#27AE60"));
        }


        valueText.setScaleX(0.9f);
        valueText.setScaleY(0.9f);
        valueText.setAlpha(0f);

        valueText.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .start();


        star.setScaleX(0f);
        star.setScaleY(0f);
        star.setAlpha(0f);
        star.setRotation(-15f);

        if (score >= 3) {
            star.postDelayed(() -> {
                star.setVisibility(View.VISIBLE);
                star.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .rotation(0f)
                        .setDuration(500)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
            }, 500);
        } else {
            star.setVisibility(View.GONE);
        }


        retryBtn.setOnClickListener(v -> {
            animateButton(retryBtn);
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("LANG", getIntent().getStringExtra("LANG"));
            //intent.putExtra("retry", true);
            startActivity(intent);
            finish();
        });


        homeBtn.setOnClickListener(v -> {
            animateButton(homeBtn);
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void animateButton(Button button) {
        button.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> button.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start())
                .start();
    }
}
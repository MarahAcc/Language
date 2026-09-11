package com.example.language;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findViewById(R.id.btnFrench).setOnClickListener(v -> openQuiz("FR"));
        findViewById(R.id.btnJapanese).setOnClickListener(v -> openQuiz("JP"));
        findViewById(R.id.btnTurkish).setOnClickListener(v -> openQuiz("TR"));

    }

    private void openQuiz(String lang) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("LANG", lang);
        startActivity(intent);
    }


}

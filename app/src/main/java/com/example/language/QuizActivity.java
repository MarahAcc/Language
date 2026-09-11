package com.example.language;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends Activity {

    TextView questionText, counterText;
    ImageView imageView;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button nextBtn;

    int currentQuestion = 0;
    int score = 0;
    int correctId;
    String language;

    boolean answered = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        counterText = findViewById(R.id.counterText);
        imageView = findViewById(R.id.imageView);
        radioGroup = findViewById(R.id.radioGroup);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        nextBtn = findViewById(R.id.nextBtn);

        language = getIntent().getStringExtra("LANG");

        setOptionsOnce();
        showQuestion();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == -1 || answered) return;
            answered = true;
            RadioButton selected = findViewById(checkedId);
            RadioButton correct = findViewById(correctId);

            if (checkedId == correctId) {
                selected.setBackgroundResource(R.drawable.radio_correct);
                score++;
            } else {
                selected.setBackgroundResource(R.drawable.radio_wrong);
                correct.setBackgroundResource(R.drawable.radio_correct);
            }
            disableOptions();
        });

        nextBtn.setOnClickListener(v -> {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Choose an answer to continue 😊", Toast.LENGTH_SHORT).show();
                return;
            }

            currentQuestion++;
            if (currentQuestion < 5) {
                showQuestion();
            } else {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("LANG", language);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setOptionsOnce() {
        if (language.equals("FR")) {
            rb1.setText("Rouge");
            rb2.setText("Bleu");
            rb3.setText("Livre");
            rb4.setText("trois");
            rb5.setText("Chat");
        } else if (language.equals("JP")) {
            rb1.setText("ミズ (Mizu)");
            rb2.setText("おはよう(ohayō)");
            rb3.setText("リンゴ (Ringo)");
            rb4.setText("赤い (Akai)");
            rb5.setText("ネコ (Neko)");
        } else if (language.equals("TR")) {
            rb1.setText("Deniz");
            rb2.setText("Konak");
            rb3.setText("Sonbahar");
            rb4.setText("Çiçek");
            rb5.setText("Yağmur");
        }
    }

    private void showQuestion() {
        counterText.setText((currentQuestion + 1) + " / 5");
        radioGroup.clearCheck();

        enableOptions();
        answered = false;

        if(language.equals("FR")) {
            switch(currentQuestion){
                case 0:
                    questionText.setText("What number is this?");
                    imageView.setImageResource(R.drawable.number3);
                    correctId = rb4.getId();
                    break;
                case 1:
                    questionText.setText("What color is this?");
                    imageView.setImageResource(R.drawable.red1);
                    correctId = rb1.getId();
                    break;
                case 2:
                    questionText.setText("what is she holding?");
                    imageView.setImageResource(R.drawable.book);
                    correctId = rb3.getId();
                    break;
                case 3:
                    questionText.setText("what color is this flower ?");
                    imageView.setImageResource(R.drawable.blueflower);
                    correctId = rb2.getId();
                    break;
                case 4:
                    questionText.setText("What animal is this?");
                    imageView.setImageResource(R.drawable.cat1);
                    correctId = rb5.getId();
                    break;
            }
        } else if(language.equals("JP")) {
            switch(currentQuestion){
                case 0:
                    questionText.setText("What is this?");
                    imageView.setImageResource(R.drawable.apple);
                    correctId = rb3.getId();
                    break;
                case 1:
                    questionText.setText("What is this?");
                    imageView.setImageResource(R.drawable.cat2);
                    correctId = rb5.getId();
                    break;
                case 2:
                    questionText.setText("What is this?");
                    imageView.setImageResource(R.drawable.water);
                    correctId = rb1.getId();
                    break;
                case 3:
                    questionText.setText("What is this color ?");
                    imageView.setImageResource(R.drawable.red2);
                    correctId = rb4.getId();
                    break;
                case 4:
                    questionText.setText("How do you say 'Good morning' in Japanese?");
                    imageView.setImageResource(R.drawable.goodmorning);
                    correctId = rb2.getId();
                    break;
            }
        } else if(language.equals("TR")) {
            switch(currentQuestion){
                case 0:
                    questionText.setText("What weather is shown in the picture?");
                    imageView.setImageResource(R.drawable.rain);
                    correctId = rb5.getId();
                    break;
                case 1:
                    questionText.setText("What weather is shown in the picture?");
                    imageView.setImageResource(R.drawable.sonbahar);
                    correctId = rb3.getId();
                    break;
                case 2:
                    questionText.setText("What is this in the picture?");
                    imageView.setImageResource(R.drawable.konak);
                    correctId = rb2.getId();
                    break;
                case 3:
                    questionText.setText("What is this in the picture?");
                    imageView.setImageResource(R.drawable.sea);
                    correctId = rb1.getId();
                    break;
                case 4:
                    questionText.setText("What is this in the picture?");
                    imageView.setImageResource(R.drawable.flower2);
                    correctId = rb4.getId();
                    break;
            }
        }
    }

    private void disableOptions() {
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        rb5.setEnabled(false);
    }

    private void enableOptions() {
        radioGroup.clearCheck();
        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rb4.setEnabled(true);
        rb5.setEnabled(true);

        rb1.setBackgroundResource(R.drawable.button_language);
        rb2.setBackgroundResource(R.drawable.button_language);
        rb3.setBackgroundResource(R.drawable.button_language);
        rb4.setBackgroundResource(R.drawable.button_language);
        rb5.setBackgroundResource(R.drawable.button_language);
    }
}
// java/com/example/autotest/EasyLevelActivity.java
package com.example.autotest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class EasyLevelActivity extends AppCompatActivity {

    private static final String TAG = "EasyLevelActivity";

    private int currentQuestionIndex = 0;
    private int correctAnswerCount = 0;
    private String[] questions = {
            "Який із зображених дорожніх знаків забороняє зупинку транспортних засобів?",
            "Чи дозволено автомобілю виконати виїзд на трамвайну колію попутного напрямку?",
            "Чим розпізнавальним знаком позначаються транспортні засоби, на яких:",
            "Чи дозволено зупинитися в цьому місці для посадки пасажирів?",
            "Чи може водій білого автомобіля виїхати на перехрестя в даній ситуації?"
    };
    private int[] images = {
            R.drawable.easy_1,
            R.drawable.easy_2,
            R.drawable.easy_3,
            R.drawable.easy_4,
            R.drawable.easy_5
    };
    private String[][] answers = {
            {"Знак 1", "Знак 2", "Знак 3", "Знак 4", "Знак 5"},
            {"Дозволено", "Заборонено"},
            {"Встановлено шини з шипами", "Встановлено зимові шини", "Встановлено всесезонні шини"},
            {"Дозволено", "Заборонено", "Дозволено тільки в світлий час доби"},
            {"Може, тому що перебуває на головній дорозі", "Не може, тому що, зупинившись, створить перешкоду для руху інших транспортних засобів", "Може, тому що рухається прямо"}
    };
    private int[] correctAnswers = {4, 0, 0, 1, 1};

    private TextView questionText;
    private ImageView questionImage;
    private Button[] answerButtons = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level);

        questionText = findViewById(R.id.question_text);
        questionImage = findViewById(R.id.question_image);
        answerButtons[0] = findViewById(R.id.answer_button_1);
        answerButtons[1] = findViewById(R.id.answer_button_2);
        answerButtons[2] = findViewById(R.id.answer_button_3);
        answerButtons[3] = findViewById(R.id.answer_button_4);
        answerButtons[4] = findViewById(R.id.answer_button_5);

        loadQuestion();

        for (int i = 0; i < answerButtons.length; i++) {
            final int index = i;
            answerButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(index);
                }
            });
        }
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestionIndex]);
        questionImage.setImageResource(images[currentQuestionIndex]);
        for (int i = 0; i < answerButtons.length; i++) {
            if (i < answers[currentQuestionIndex].length) {
                answerButtons[i].setText(answers[currentQuestionIndex][i]);
                answerButtons[i].setVisibility(View.VISIBLE);
            } else {
                answerButtons[i].setVisibility(View.GONE);
            }
        }
    }

    private void checkAnswer(int index) {
        if (index == correctAnswers[currentQuestionIndex]) {
            correctAnswerCount++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            loadQuestion();
        } else {
            showResults();
        }
    }

    private void showResults() {
        Log.d(TAG, "Showing results");

        // Показать результаты через SnackBar
        Snackbar.make(findViewById(android.R.id.content),
                "Правильные ответы: " + correctAnswerCount + "/5",
                Snackbar.LENGTH_LONG).show();

        // Вернуться на главный экран после небольшой задержки
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(EasyLevelActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Snackbar.LENGTH_LONG + 1000); // Добавляем дополнительное время, чтобы SnackBar успел показаться
    }
}

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

public class HardLevelActivity extends AppCompatActivity {

    private static final String TAG = "EasyLevelActivity";

    private int currentQuestionIndex = 0;
    private int correctAnswerCount = 0;
    private String[] questions = {
            "Повертаючи ліворуч у даній ситуації, Ви повинні:",
            "Виїжджаючи з пішохідної зони, водій синього автомобіля повинен дати дорогу:",
            "Чи має право водій білого автомобіля в даній ситуації виїхати і зупинитися на смузі для маршрутних транспортних засобів для посадки пасажира?",
            "Як водій білого автомобіля, що наближається до перехрестя, повинен проїхати дане перехрестя?",
            "Чи повинен водій жовтого автомобіля зупинитися перед стоп-лінією, якщо він вже зупинявся за попутним автомобілем?"
    };
    private int[] images = {
            R.drawable.hard1,
            R.drawable.hard2,
            R.drawable.hard3,
            R.drawable.hard4,
            R.drawable.hard5
    };
    private String[][] answers = {
            {"Проїхати першим", "Дати дорогу велосипедистові"},
            {"Пішоходу", "Автомобілю", "Пішоходу і автомобілю"},
            {"Має право", "Не має права: виїзд на смугу йому заборонено"},
            {"Зупинитися перед розміткою Стоп-Лінія і дали продовжити рух", "Зупинитися перед розміткою Стоп-Лінія і давши дорогу іншим транспортним автомобілям, продовжити рух", "Проїхати перехрестя без зупинки"},
            {"Не повинен", "Повинен"}
    };
    private int[] correctAnswers = {2, 3, 1, 3, 2};

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
                Intent intent = new Intent(HardLevelActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Snackbar.LENGTH_LONG + 1000); // Добавляем дополнительное время, чтобы SnackBar успел показаться
    }
}

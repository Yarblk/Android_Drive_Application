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

public class MediumLevelActivity extends AppCompatActivity {

    private static final String TAG = "EasyLevelActivity";

    private int currentQuestionIndex = 0;
    private int correctAnswerCount = 0;
    private String[] questions = {
            "Яким із розпізнавальних знаків позначаються транспортні засоби, якими керують водії з інвалідністю",
            "Як Ви повинні проїхати дане перехрестя",
            "Чи дозволено обгін у даній ситуації",
            "Хто з водіїв повинен дати дорогу в даній ситуації",
            "Чи дозволено Вам у зображеній ситуації рух у прямому напрямку?"
    };
    private int[] images = {
            R.drawable.medium1,
            R.drawable.medium2,
            R.drawable.medium3,
            R.drawable.medium4,
            R.drawable.medium5
    };
    private String[][] answers = {
            {"Першим", "Другим", "Третім", "Четвертим"},
            {"Дати дорогу фіолетовому авто", "Проїхати першим, не даючи дорогу фіолетовому авто"},
            {"Дозволено", "Дозволено за умови, що швидкість автомобіля який рухається попереду, менше 30 км/год", "Заборонено"},
            {"Водій автомобіля", "Водій трамвая"},
            {"Дозволено, якщо ви там проживаєте", "Дозволено, якщо ви обслуговуєте підприємства, що там розташовані", "Заборонено", "Відповіді 1 і 2"}
    };
    private int[] correctAnswers = {1, 1, 3, 1, 3};

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
                Intent intent = new Intent(MediumLevelActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Snackbar.LENGTH_LONG + 1000); // Добавляем дополнительное время, чтобы SnackBar успел показаться
    }
}

package com.example.voctorina;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_Next, btn_true, btn_false, btn_Restart;
    private TextView textView;
    private int quest_index = 0;
    private int count = 0;

    private TrueFalse[] questions = new TrueFalse[]{
            new TrueFalse(R.string.quest_1, true),
            new TrueFalse(R.string.quest_2, false),
            new TrueFalse(R.string.quest_3, true),
            new TrueFalse(R.string.quest_4, true),
            new TrueFalse(R.string.quest_5, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_false = findViewById(R.id.noBtn);
        btn_true = findViewById(R.id.yesBtn);
        btn_Next = findViewById(R.id.nextBtn);
        textView = findViewById(R.id.textId);
        btn_Restart = findViewById(R.id.Restart);

        final int question = questions[quest_index].getQuestion();
        textView.setText(question);

        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        btn_Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                quest_index = 0;
                final int question = questions[quest_index].getQuestion();
                textView.setText(question);
                textView.setTextColor(Color.BLACK);
                btn_true.setVisibility(View.VISIBLE);
                btn_false.setVisibility(View.VISIBLE);
                btn_Restart.setVisibility(View.INVISIBLE);

            }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quest_index < questions.length - 1) {
                    quest_index++;
                    final int question = questions[quest_index].getQuestion();
                    textView.setText(question);
                    btn_true.setVisibility(View.VISIBLE);
                    btn_false.setVisibility(View.VISIBLE);
                    btn_Next.setVisibility(View.INVISIBLE);

                } else {
                    textView.setText("Вы прошли викторину! " + count + "/" + questions.length);
                    textView.setTextColor(Color.GREEN);
                    btn_Next.setVisibility(View.INVISIBLE);
                    btn_Restart.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void restartGame() {
    }

    private void checkAnswer(boolean answerUser) {
        boolean answer = questions[quest_index].isTrueQuestion();
        if (answerUser == answer) {
            Toast.makeText(MainActivity.this, "Вы ответили правильно!", Toast.LENGTH_LONG).show();
            count++;
        } else
            Toast.makeText(MainActivity.this, "Вы ответили неправильно!", Toast.LENGTH_LONG).show();

        btn_true.setVisibility(View.INVISIBLE);
        btn_false.setVisibility(View.INVISIBLE);
        btn_Next.setVisibility(View.VISIBLE);


    }
}
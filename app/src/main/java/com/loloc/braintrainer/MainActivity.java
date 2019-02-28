package com.loloc.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    TextView result;
    TextView points;
    TextView exercise;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    TextView countdown;
    CountDownTimer timer;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void generateQuestion() {

            Random rand = new Random();

            int a = rand.nextInt(21);
            int b = rand.nextInt(21);

            exercise.setText(Integer.toString(a) + "+" + Integer.toString(b));

            locationOfCorrectAnswer = rand.nextInt(4);

            int incorrectAnswer;

            for (int i = 0; i < 4; i++) {

                if (i == locationOfCorrectAnswer) {

                    answers.add(a + b);

                } else {

                    incorrectAnswer = rand.nextInt(41);

                    while (incorrectAnswer == a + b) {

                        incorrectAnswer = rand.nextInt(41);

                    }

                    answers.add(incorrectAnswer);

                }

            }
            answer1.setText(Integer.toString(answers.get(0)));
            answer2.setText(Integer.toString(answers.get(1)));
            answer3.setText(Integer.toString(answers.get(2)));
            answer4.setText(Integer.toString(answers.get(3)));
        }

    public void start(View view) {

        answers.clear();

        generateQuestion();

        startButton.setVisibility(View.INVISIBLE);

        score = 0;
        numberOfQuestions = 0;

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                countdown.setText(String.valueOf(millisUntilFinished / 1000)+ "s");
                Log.i("millis", String.valueOf(millisUntilFinished));

            }

            @Override
            public void onFinish() {

             startButton.setVisibility(View.VISIBLE);
             result.setText("Try Again");
             countdown.setText("30s");

            }
        }.start();

    }

    public void chooseAnswer(View view) {

            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

                Log.i("correct", "correct");
                score++;
                result.setText("correct");

            } else {

                result.setText("Wrong");

            }

            numberOfQuestions++;
            points.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            answers.clear();
            generateQuestion();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start);
        exercise = findViewById(R.id.exercise);
        answer1 = findViewById(R.id.textView1);
        answer2 = findViewById(R.id.textView2);
        answer3 = findViewById(R.id.textView3);
        answer4 = findViewById(R.id.textView4);
        result = findViewById(R.id.result);
        points = findViewById(R.id.points);
        countdown = findViewById(R.id.countDown);

    }
}

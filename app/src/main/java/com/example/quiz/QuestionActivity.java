package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    TextView lblQuestion;
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    Button confirm;
    String rightAnswer;
    String answer;
    List<Question> questions;
    int score;
    int questionIndex = 0;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.next);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        score = 0;

        questions = new ArrayList<Question>(){
            {
                add(new Question("Which of the following is not a web browser?", "C", "MOSAIC", "WWW", "Facebook", "Netscape navigator"));
                add(new Question("Snow removal vehicles have flashing lights of the following color:", "D", "Red", "Yellow","Green", "Blue"));
                add(new Question("As a level G2 driver, your blood alcohol level must not be over: ", "D", " 0.08%", "0.05%"," 0.02%", "0.00%"));
            }
        };

        loadQuestion(view);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion(view);
    }


    public void loadQuestion(View view){
        System.out.println("ii" + questionIndex + questions.size());
        if(questions.size() > 0 && questionIndex < questions.size()) {
            Question q = questions.get(questionIndex);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
            questionIndex += 1;
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void selectAnswer(View view) {
        switch (view.getId()) {
            case R.id.optionA:
                answer = "A";
                updateSelectedBtnBg(R.id.optionA);
                break;
            case R.id.optionB:
                answer = "B";
                updateSelectedBtnBg(R.id.optionB);
                break;
            case R.id.optionC:
                answer = "C";
                updateSelectedBtnBg(R.id.optionC);
                break;
            case R.id.optionD:
                answer = "D";
                updateSelectedBtnBg(R.id.optionD);
                break;
        }

        updateScore(answer);
    }

    private void updateSelectedBtnBg(int id) {
        Button button = findViewById(id);
        button.setBackgroundColor(getResources().getColor(R.color.green));
    }

    private void resetAllBtnBgColor() {

    }

    private void updateScore(String answer){
        if(answer.equals(rightAnswer)) {
            this.score += 1;
        }
    }
}

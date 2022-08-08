package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    TextView lblQuestion, questionIdx;
    ProgressBar progressBar;
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    Button next;
    String rightAnswer;
    String answer;
    List<Question> questions, allQuestions;
    int score;
    int questionIndex = 0;
    int progressStatus = 0;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        next = findViewById(R.id.next);
        lblQuestion = findViewById(R.id.lblPergunta);
        questionIdx = findViewById(R.id.questionIdx);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        int MAX_QUESTION = 5;

        score = 0;

         questions = new ArrayList<Question>();
         allQuestions = new ArrayList<Question>(){
            {
                add(new Question("In Ontario, a first conviction for drinking and driving can bring a minimum license suspension of:", "B", "6 months", "12 months"," 24 months", "48 months"));
                add(new Question("Under the Graduated Licensing System in Ontario, the supervising driver must not let their Blood Alcohol Concentration exceed:", "C", "0.00 g%", "0.08 g%"," 0.05 g%", "0.80 g%"));
                add(new Question("In Ontario, a solid yellow line at the left of your lane means:", "B", "It is illegal to pass", "It is unsafe to pass ", ". It is illegal to cross over ", "It is legal to pass "));
                add(new Question("Snow removal vehicles have flashing lights of the following color:", "D", "Red", "Yellow", "Green", "Blue"));
                add(new Question("As a level G2 driver, your blood alcohol level must not be over: ", "D", "0.08%","0.05%","0.02%", "0.00%"));
                add(new Question("Unless otherwise posted, the maximum speed limit in cities, towns, villages and built up area is: ", "A", "50 km/hr", "40 km/hr", " 30 km/hr", "60 km/hr"));
                add(new Question("If your phone rings while driving:", "A", "All answers are correct", "Pull over and park to use your cell phone", "Have a passenger take the call", "Let it go to voice mail"));
                add(new Question("How many meters in both directions must drivers be able to see in order to make a legal U-turn?", "C", "250 metres", "100 metres", "150 metres", "50 metres"));
                add(new Question("As you approach the curve….?", "A", "Try to determine the safe speed for it", "Speed up", "Slam on your brakes", "Stop before entering"));
                add(new Question("When arriving at an intersection which has no stop line, crosswalk or sidewalk, where must drivers stop?", "C", "Right before the stop sign", "Right beside the stop sign", "At the edge of the intersection", "A little into the intersection"));
            }
        };

         Collections.shuffle(allQuestions);

         for (int i=0; i<MAX_QUESTION; i++) {
             questions.add(allQuestions.get(i));
         }

        loadQuestion(view);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion(view);
    }

    public void loadQuestion(View view){
        resetAllBtnBgColor();
//        setNextBtnVisibility();
        progressStatus = progressStatus + 20;
        progressBar.setProgress(progressStatus);

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

        questionIdx.setText(questionIndex + "/" + questions.size());
    }

    public void selectAnswer(View view) {
        resetAllBtnBgColor();
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
//        setNextBtnVisibility();
    }

    private void updateSelectedBtnBg(int id) {
        Button button = findViewById(id);
        button.setBackground(getResources().getDrawable(R.drawable.selected_answer));
    }

    private void setNextBtnVisibility() {
        if (rightAnswer == null || rightAnswer == "" ) {
            next.setEnabled(false);
            next.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
            next.setTextColor(getColor(R.color.disabled));
        } else {
            next.setEnabled(true);
            next.setTextColor(getColor(R.color.black));
            next.setBackground(getResources().getDrawable(R.drawable.btn_orange));
        }
    }

    private void resetAllBtnBgColor() {
        Button option1 = findViewById(R.id.optionA);
        Button option2 = findViewById(R.id.optionB);
        Button option3 = findViewById(R.id.optionC);
        Button option4 = findViewById(R.id.optionD);

        option1.setBackground(getResources().getDrawable(R.drawable.button_border));
        option2.setBackground(getResources().getDrawable(R.drawable.button_border));
        option3.setBackground(getResources().getDrawable(R.drawable.button_border));
        option4.setBackground(getResources().getDrawable(R.drawable.button_border));
    }

    private void updateScore(String answer){
        if(answer.equals(rightAnswer)) {
            this.score += 1;
        }
    }
}

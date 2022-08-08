package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowScoreActivity extends AppCompatActivity {
    TextView TxtScore;
    TextView TxtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        TxtScore = findViewById(R.id.txtscore);
        TxtStatus = findViewById(R.id.txtStatus);
        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));

        TxtScore.setText(scores);
        TxtStatus.setText(setStatus(scores));

    }

    private String setStatus(String scores){
        int score = Integer.parseInt(scores);

        switch (score) {
            case 0:
            case 1:
            case 2:
                return "Please try again!";
            case 3:
                return "Good job!";
            case 4:
                return "Excellent work!";
            case 5:
                return "You are a genius!";
            default:
                return "";
        }
    }


    public void goToHome(View v){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();
    }

}

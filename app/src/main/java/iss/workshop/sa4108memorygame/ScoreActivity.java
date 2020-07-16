package iss.workshop.sa4108memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();


        /*TextView playerName = (TextView) findViewById(R.id.playerName);
        TextView playerScore = (TextView) findViewById(R.id.playerScore);*/



        float score = intent.getFloatExtra("score", 10.0f);
    }
}
package iss.workshop.sa4108memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button start;
    private Button scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        scoreboard = (Button) findViewById(R.id.scoreboard);
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScoreboard();
            }
        });

    }

    public void openScoreboard() {
        Intent scoreIntent = new Intent(ResultActivity.this, ScoreActivity.class);
        scoreIntent.putExtra("name", "Team 3");
        startActivity(scoreIntent);

    }

    public void openMainActivity() {
        Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
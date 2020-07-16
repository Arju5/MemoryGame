package iss.workshop.sa4108memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        scoreboard = (Button) findViewById(R.id.scoreboard);
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScoreboard();
            }
        });

    }

    public void openScoreboard() {
        Intent intent = new Intent(ResultActivity.this, ScoreActivity.class);
        intent.putExtra("name", "Team 3");
        startActivity(intent);

    }
}
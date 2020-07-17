package iss.workshop.sa4108memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView rank, name, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();


        /*TextView playerName = (TextView) findViewById(R.id.playerName);
        TextView playerScore = (TextView) findViewById(R.id.playerScore);
        float score = intent.getFloatExtra("score", 10.0f);*/
        TableLayout table = (TableLayout) findViewById(R.id.table);
        for(int i = 1; i < 6 ; i ++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setShowDividers(LinearLayout.SHOW_DIVIDER_END);
            rank = new TextView(this);
            rank.setText("4");
            rank.setTextSize(20);
            TableRow.LayoutParams parameters1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
            rank.setLayoutParams(parameters1);
            rank.setGravity(Gravity.CENTER);
            name = new TextView(this);
            name.setText("Team 3");
            TableRow.LayoutParams parameters2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            name.setLayoutParams(parameters2);
            name.setTextSize(20);
            name.setGravity(Gravity.CENTER);
            time = new TextView(this);
            time.setText("Score");
            time.setTextSize(20);
            time.setLayoutParams(parameters2);
            time.setGravity(Gravity.CENTER);
            tableRow.addView(rank);
            tableRow.addView(name);
            tableRow.addView(time);
            table.addView(tableRow);
        }

    }
}
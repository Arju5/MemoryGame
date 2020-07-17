package iss.workshop.sa4108memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class StartPage extends AppCompatActivity {
    private ImageButton btn_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        btn_start = (ImageButton) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

    }

    public void openMainActivity() {
        Intent mainIntent = new Intent(StartPage.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
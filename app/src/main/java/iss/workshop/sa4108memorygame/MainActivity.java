package iss.workshop.sa4108memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //testdata
    private String[] cartoons = {
            "hug", "laugh", "peep", "snore", "stop",
            "tired", "full", "what", "afraid", "no_way"
    };

    List<String> testlist1 = new ArrayList<String>(Arrays.asList(cartoons));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button mButtonFetch = findViewById(R.id.button_fetch);
        if (mButtonFetch !=null){
            mButtonFetch.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_fetch:
                ImageAdapter imgAdapter =new ImageAdapter(this,R.layout.image_row, (ArrayList<String>) this.testlist1);
                ListView listView1 = findViewById(R.id.listView1);
                listView1.setEnabled(false);
                if (listView1 != null){
                    listView1.setAdapter(imgAdapter);
                }
                break;

        }

    }
}
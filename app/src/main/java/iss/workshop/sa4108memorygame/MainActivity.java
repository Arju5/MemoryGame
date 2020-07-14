package iss.workshop.sa4108memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

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
                GridView gridView1 = findViewById(R.id.gridView1);
                gridView1.setEnabled(false);
                gridView1.setNumColumns(4);
                if (gridView1 != null){
                    gridView1.setAdapter(imgAdapter);
                    gridView1.setOnItemClickListener(this);

                }
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            //This is still not working well - need to resolve
            case R.id.img1:
                System.out.println("This is onItemClick");
        }

    }
}
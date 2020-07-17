package iss.workshop.sa4108memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button mButtonFetch;
    private EditText mEditTextUrl;
    private ProgressBar mProgressBar;
    private int counter = 0;
    private String[] htmlStringArray;
    private ArrayList<String> selectedPictureArray = new ArrayList<String>() ;

    public void setHtmlStringArray(String[] htmlStringArray) {
        this.htmlStringArray = htmlStringArray;
    }

    public String[] getHtmlStringArray() {
        return htmlStringArray;
    }

    @SuppressLint("HandlerLeak")
    Handler mainThreadHandler = new Handler() {
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                setHtmlStringArray((String[]) msg.obj);
                System.out.println(getHtmlStringArray().toString());
                System.out.println("This is the first url i want to use: " + htmlStringArray[0]);
            }
        }
    };

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

//      startActivity(new Intent(MainActivity.this, ResultActivity.class));

        mButtonFetch = findViewById(R.id.button_fetch);
        if (mButtonFetch !=null){
            mButtonFetch.setOnClickListener(this);

        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_fetch:
                mEditTextUrl = findViewById(R.id.edit_text_url);
                String urlString = mEditTextUrl.getText().toString();
                System.out.println(urlString);
                Thread thread = new URLParserThread(urlString,MainActivity.this, mainThreadHandler);
                thread.start();

                ImageAdapter imgAdapter =new ImageAdapter(this,R.layout.image_row, (ArrayList<String>) this.testlist1);
                GridView gridView1 = findViewById(R.id.gridView1);
                gridView1.setNumColumns(4);
                if (gridView1 != null){
                    gridView1.setAdapter(imgAdapter);
                    //this is not working normally for now
                    gridView1.setOnItemClickListener(this);
                }
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        // checking index
        System.out.println("Index: " + String.valueOf(index));
        System.out.println("L: " + String.valueOf(l));
        //Still working on this
        if (this.selectedPictureArray.contains(htmlStringArray[index])){
            String expr = "You have selected this image already. \n Please select another 1";
            Toast toast = Toast.makeText(this, expr, Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            counter +=1;
            this.selectedPictureArray.add(htmlStringArray[index]);
            new SoundPoolPlayer(this).playSoundWithRedId(R.raw.click);
        }


        if (counter == 6){
            System.out.println(selectedPictureArray);
            System.out.println(counter);
            counter = 0;
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("pictureList",selectedPictureArray);
            System.out.println(intent.getStringArrayListExtra("pictureList"));
            startActivity(intent);


        }
    }
}
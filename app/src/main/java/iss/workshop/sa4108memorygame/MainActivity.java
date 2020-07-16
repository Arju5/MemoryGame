package iss.workshop.sa4108memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

    Button mButtonFetch;
    EditText mEditTextUrl;
    ProgressBar mProgressBar;

    private HandlerThread ht;
    private Handler hdl;


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

        ht = new HandlerThread("bg_thread_1");
        ht.start();



        mButtonFetch = findViewById(R.id.button_fetch);
        if (mButtonFetch !=null){
            mButtonFetch.setOnClickListener(this);
            //check if urlString is registering properly

        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_fetch:
                mEditTextUrl = findViewById(R.id.edit_text_url);
                String urlString = mEditTextUrl.getText().toString();
                System.out.println(urlString);

                Thread thread = new URLParserThread(urlString);
                thread.start();

                ImageAdapter imgAdapter =new ImageAdapter(this,R.layout.image_row, (ArrayList<String>) this.testlist1);
                GridView gridView1 = findViewById(R.id.gridView1);
                gridView1.setEnabled(false);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


}
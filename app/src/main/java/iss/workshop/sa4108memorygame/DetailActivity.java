package iss.workshop.sa4108memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DetailActivity extends AppCompatActivity {

    ImageView currentView = null;
    TextView timer_txt;
    private int countPair = 0;
    boolean timer_start = true;

    long start_time = 0;


    class DetailTask extends TimerTask {

        @Override
        public void run() {
            DetailActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    long millis = System.currentTimeMillis() - start_time;
                    int seconds = (int) (millis / 1000);
                    int minutes = seconds / 60;
                    int hours = minutes / 60;
                    seconds     = seconds % 60;

                    timer_txt.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                }
            });

        }
    };

    final int[] selectedImg = new int[]{
            R.drawable.hug,R.drawable.laugh,R.drawable.peep,
            R.drawable.snore,R.drawable.stop,R.drawable.tired,
            R.drawable.hug,R.drawable.laugh,R.drawable.peep,
            R.drawable.snore,R.drawable.stop,R.drawable.tired
            };
//    final String[] selectedImg = new String[]{
//         "hug", "laugh", "peep", "snore", "stop","tired"};

    int[] pos = {0,1,2,3,4,5,0,1,2,3,4,5};
    int currentPos = -1;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        timer_txt = (TextView)findViewById(R.id.timer);
        if(timer_start){
            start_time = System.currentTimeMillis();
            timer = new Timer();
            timer.schedule(new DetailTask(),  0,500);
            //h2.postDelayed(run, 0);
        }
        else{

        }

        GridView gridView = (GridView) findViewById(R.id.gridView2);
        ImageAdapter2 adapter = new ImageAdapter2(this);
        if (gridView != null) {
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(currentPos < 0 ){
                        currentPos = i;
                        currentView = (ImageView) view;
                        ((ImageView)view).setImageResource(selectedImg[pos[i]]);
                    }
                    else
                    {
                        if(currentPos == i)
                        {
                            ((ImageView)view).setImageResource(R.drawable.question);
                        }
                        else if(pos[currentPos]!= pos[i])
                        {
                            currentView.setImageResource(R.drawable.question);
                            Toast.makeText(getApplicationContext(),"Not match",Toast.LENGTH_LONG).show();
                        }
                        else{
                            ((ImageView)view).setImageResource(selectedImg[pos[i]]);
                            countPair++;
                            if(countPair ==0)
                            {
                                Toast.makeText(getApplicationContext(),"You Win",Toast.LENGTH_LONG).show();
                            }
                        }
                        currentPos = -1;
                    }
                }
            });
        }
    }


}
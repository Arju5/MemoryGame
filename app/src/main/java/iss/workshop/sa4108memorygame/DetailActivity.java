package iss.workshop.sa4108memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DetailActivity extends AppCompatActivity {

    ImageView currentView = null;
    TextView timer_txt;
    private int countPair = 0;
    boolean timer_start = true;
    long start_time = 0;
    private boolean isInGame = true;
    private String firstImg;
    private String secondImg;
    private int[] randomImage;
    private int numOfImages = 12;
    //private int[] imageGraphic = new int[numOfImages/2];
    private int[] imageGraphicLocation = new int[numOfImages];

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
                    seconds = seconds % 60;

                    timer_txt.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                }
            });
        }
    }

    ;

    final int[] selectedImg = new int[]{
            R.drawable.hug, R.drawable.laugh, R.drawable.peep,
            R.drawable.snore, R.drawable.stop, R.drawable.tired,
            R.drawable.hug, R.drawable.laugh, R.drawable.peep,
            R.drawable.snore, R.drawable.stop, R.drawable.tired
    };

    int firstClick = 0;
    int secondClick = 0;
    ImageView firstview = null;
    ImageView secondview = null;

    boolean isBusy = false;
    boolean isFlipped1 = false;
    boolean isFlipped2 = false;
    boolean isMatched = false;
    String countMsg;

//    final String[] selectedImg = new String[]{
//         "hug", "laugh", "peep", "snore", "stop","tired"};

    int[] pos = {0,1,2,3,4,5,0,1,2,3,4,5};
    int currentPos = -1;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        timer_txt = (TextView) findViewById(R.id.timer);
        if (timer_start) {
            start_time = System.currentTimeMillis();
            timer = new Timer();
            timer.schedule(new DetailTask(), 0, 500);
            //h2.postDelayed(run, 0);
        } else {

        }
        GridView gridView = (GridView) findViewById(R.id.gridView2);
        ImageAdapter2 adapter = new ImageAdapter2(this);
        //shuffleImage();
        if (gridView != null) {
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    if(((ImageView)view).isEnabled() == true){
                        if(isBusy){
                            return;
                        }
                        if(isMatched){
                            return;
                        }
                        if(firstview == null){
                            firstClick = selectedImg[pos[i]];

                            firstview = (ImageView)view;
                            //firstview.setEnabled(false);
                            flip(firstview,i,"first");

                            return;
                        }
                    /*if(firstview.getId() == view.getId()){
                        return;
                    }*/
                        System.out.println("FirstViewID : " + firstClick);
                        System.out.println("SecondViewID : " + selectedImg[pos[i]]);

                        if(firstClick != selectedImg[pos[i]]){
                            secondview = (ImageView) view;
                            flip(secondview,i,"second");
                            isBusy = true;

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    flip(secondview,0,"second");
                                    flip(firstview,0,"first");
                                    firstview = null;
                                    secondview = null;
                                    isBusy = false;
                                }
                            },1000);
                        }
                        else {
                            secondview = (ImageView) view;
                            flip(secondview, i, "second");

                            firstview.setEnabled(false);
                            secondview.setEnabled(false);
                            //increasing count of matches
                            //isMatched = true;
                            countPair++;
                            countMsg = countPair+"/6 Matches";
                            TextView count = findViewById(R.id.NoOfMatches);
                            if(count!=null) {
                                count.setText(countMsg);
                            }
                            if(countPair == 6){
                                isMatched = true;

                                timer.cancel();
                                timer.purge();

                                //b.setText("start");
                                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(),"Congratulations!!! You win.",Toast.LENGTH_LONG).show();
                            }

                            firstview = null;
                            secondview = null;
                            isFlipped1 = false;
                            isFlipped2 = false;
                            isBusy = false;

                        }
                    }
                }
            });
        }
    }

    public void flip(ImageView view,int id, String level){
        if(isMatched){
            return;
        }
        if(level == "first"){
            if(isFlipped1){
                view.setImageResource(R.drawable.question);
                isFlipped1 = false;
            }
            else{
                view.setImageResource(selectedImg[pos[id]]);
                isFlipped1 = true;
            }
        }
        if(level == "second"){
            if(isFlipped2){
                view.setImageResource(R.drawable.question);
                isFlipped2 = false;
            }
            else{
                view.setImageResource(selectedImg[pos[id]]);
                isFlipped2 = true;
            }
        }

    }

    protected void shuffleImage() {
        Random rand = new Random();

        for (int i = 0; i < numOfImages; i++) {
            this.imageGraphicLocation[i] = i % (numOfImages / 2);
        }
        for (int i = 0; i < numOfImages; i++) {//swap location
            int temp = this.imageGraphicLocation[i];
            int swapIndex = rand.nextInt(16);
            imageGraphicLocation[i] = imageGraphicLocation[swapIndex];
            imageGraphicLocation[swapIndex] = temp;
        }
    }

}
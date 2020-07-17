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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreActivity extends AppCompatActivity {

    TextView rank, name, time;
    int count=1;
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();


        /*TextView playerName = (TextView) findViewById(R.id.playerName);
        TextView playerScore = (TextView) findViewById(R.id.playerScore);
        float score = intent.getFloatExtra("score", 10.0f);*/

        String filePath = "ScoreBoard";
        String fileName = "ScoreBoard.txt";

        File mTargetFile = new File(this.getFilesDir(), filePath + "/" +fileName);

       /* try{
            File parent = mTargetFile.getParentFile();
            if(!parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create directory: " + parent);
            }
            FileOutputStream fos = new FileOutputStream(mTargetFile);
            String fileContent = "KK,205\n" +
                    "Yamone,202\n" +
                    "Thinn,204\n" +
                    "Rohan,205\n" +
                    "Yanbin,206\n" +
                    "Sheryl,201\n" +
                    "Xinqui,206\n" +
                    "Arjun,209\n";
            fos.write(fileContent.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        TableLayout table = (TableLayout) findViewById(R.id.table);
       try {
            FileInputStream fis = new FileInputStream(mTargetFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] tokens = strLine.split(",");
                users.add(new User(tokens[0], Integer.valueOf(tokens[1])));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // Sorting the users in the list based on the time in seconds
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getTime()-t1.getTime() != 0 ? user.getTime()-t1.getTime():user.getName().compareTo(t1.getName());
            }
        });
        // Displaying the list of users in the activity_score
        for(User user : users) {
            System.out.println(user.toString());
            TableRow tableRow = new TableRow(this);
            tableRow.setShowDividers(LinearLayout.SHOW_DIVIDER_END);
            rank = new TextView(this);
            rank.setText(String.valueOf(count));
            rank.setTextSize(20);
            TableRow.LayoutParams parameters1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
            rank.setLayoutParams(parameters1);
            rank.setGravity(Gravity.CENTER);
            name = new TextView(this);
            name.setText(user.getName());
            name.setTextSize(20);
            TableRow.LayoutParams parameters2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            name.setLayoutParams(parameters2);
            name.setGravity(Gravity.CENTER);
            time = new TextView(this);
            time.setText(String.valueOf(user.getTime()));
            time.setTextSize(20);
            time.setLayoutParams(parameters2);
            time.setGravity(Gravity.CENTER);
            tableRow.addView(rank);
            tableRow.addView(name);
            tableRow.addView(time);
            table.addView(tableRow);
            count++;
        }



    }
}
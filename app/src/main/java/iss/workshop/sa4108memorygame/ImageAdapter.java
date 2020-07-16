package iss.workshop.sa4108memorygame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {
    private int count;
    private ArrayList<String> imgList;


    private Context context;

    public ImageAdapter(Context context, int resId, ArrayList<String> stringArrayList) {
        super(context, resId, stringArrayList);
        this.context = context;
        this.imgList = stringArrayList;

    }

    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.image_row, null);
        view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = view.getId();
                        System.out.println(id);
                        //Need to change test text to a proper id
                        Toast.makeText(context, "test text", Toast.LENGTH_LONG).show();

            }
        });
        ImageView img1 = view.findViewById(R.id.img1);


        int id1 = context.getResources().getIdentifier(imgList.get(pos), "drawable", context.getPackageName());
        img1.setImageResource(id1);
//        img1.setTag(imgList.get(id1));


        return view;

    }

}

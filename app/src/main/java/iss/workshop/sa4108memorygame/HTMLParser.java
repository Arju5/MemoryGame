package iss.workshop.sa4108memorygame;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTMLParser extends AsyncTask<Void, Void, Void> {


    private Context context;
    private String urlString;

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public HTMLParser(String urlString,Context context) {
        this.urlString = urlString;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    public String CreateHTMLString() throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            // get inputstream to read the bits
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            //get a reader to turn the bits into a String
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //read one line at a time
            String inputLine = bufferedReader.readLine();
            System.out.println(inputLine);
            while (inputLine!=null){
                // add this to our string builder
                sb.append(inputLine);
                //read next line
                inputLine = bufferedReader.readLine();

            }

        }
        finally{
            urlConnection.disconnect();
        }
        return  sb.toString();
    }



    protected void writeToFile(String htmlString){
        try{
            String filePath = "HTMLStringFolder";
            String fileName = "HTMLStringFile.txt";
            System.out.println(context.getFilesDir());
            File mTargetFile = new File(context.getFilesDir(), filePath + "/" + fileName);

            File parent = mTargetFile.getParentFile();
            if(!parent.exists() && !parent.mkdirs()){
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            FileOutputStream fos = new FileOutputStream(mTargetFile);
            fos.write(htmlString.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

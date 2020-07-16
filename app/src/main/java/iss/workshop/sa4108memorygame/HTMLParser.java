package iss.workshop.sa4108memorygame;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTMLParser extends AsyncTask<Void, Void, Void> {



    private String urlString;

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public HTMLParser(String urlString) {
        this.urlString = urlString;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    public String CreateHTMLString() throws IOException {

        HTMLParser htmlParser = new HTMLParser(urlString);

        System.out.println(urlString);
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
}

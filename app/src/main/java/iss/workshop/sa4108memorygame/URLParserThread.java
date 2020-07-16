package iss.workshop.sa4108memorygame;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.util.Arrays;

public class URLParserThread extends Thread{
    private String url;
    private Context context;


    public URLParserThread(String url,Context context){
        super();
        this.url = url;
        this.context = context;
    }

    @Override
    public void run() {


        HTMLParser htmlParser = new HTMLParser(url,context);
        try {
            String htmlString = htmlParser.CreateHTMLString();
//            System.out.println(htmlString);
            htmlParser.writeToFile(htmlString);

            String[] htmlStringArray = htmlString.split("\n");
//            System.out.println(Arrays.toString(htmlStringArray));

            int counter = 0;
            for (int i = 0; i < htmlStringArray.length; i++)
            {
                while (i<20)
                {
                    System.out.println(htmlStringArray[i]);
                }
            }

            Looper mainThreadLooper = Looper.getMainLooper(); // --> Looper of the main/UI thread
            Handler mainThreadHandler = new Handler(mainThreadLooper); // --> Get handler to main thread
            Message messageToSendToMainThread = Message.obtain(); // --> Create a message to send to UI thread
            messageToSendToMainThread.obj = htmlStringArray; // htmlString -> actual msg value
            mainThreadHandler.sendMessage(messageToSendToMainThread);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

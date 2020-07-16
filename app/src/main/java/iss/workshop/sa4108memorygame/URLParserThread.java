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
    private Handler handler;
    private Handler handler2;



    public URLParserThread(String url,Context context, Handler handler){
        super();
        this.url = url;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        Looper.prepare();
        handler2 = new Handler();

        HTMLParser htmlParser = new HTMLParser(url,context);
        try {
            String htmlString = htmlParser.CreateHTMLString();
//            System.out.println(htmlString);
            htmlParser.writeToFile(htmlString);

            String[] htmlStringArray = htmlString.split("\n");
            System.out.println(Arrays.toString(htmlStringArray));

//            Looper mainThreadLooper = Looper.getMainLooper(); // --> Looper of the main/UI thread
            Message messageToSendToMainThread = Message.obtain(); // --> Create a message to send to UI thread
            messageToSendToMainThread.obj = htmlStringArray; // htmlString -> actual msg value
            messageToSendToMainThread.what = 1;
            handler.sendMessage(messageToSendToMainThread);
            Looper.loop();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

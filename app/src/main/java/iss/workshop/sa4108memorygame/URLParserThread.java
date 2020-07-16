package iss.workshop.sa4108memorygame;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;

public class URLParserThread extends Thread{
    private String url;


    public URLParserThread(String url){
        super();
        this.url = url;
    }

    @Override
    public void run() {


        HTMLParser htmlParser = new HTMLParser(url);
        try {
            String htmlString = htmlParser.CreateHTMLString();
            System.out.println(htmlString);

            Looper mainThreadLooper = Looper.getMainLooper(); // --> Looper of the main/UI thread
            Handler mainThreadHandler = new Handler(mainThreadLooper); // --> Get handler to main thread
            Message messageToSendToMainThread = Message.obtain(); // --> Create a message to send to UI thread
            messageToSendToMainThread.obj = htmlString; // htmlString -> actual msg value
            mainThreadHandler.sendMessage(messageToSendToMainThread);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

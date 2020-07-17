package iss.workshop.sa4108memorygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class URLParserThread extends Thread{
    private String url;
    private Context context;
    private Handler handler;
    public int PROGRESS_UPDATE = 1;
    public int DOWNLOAD_COMPLETED = 2;

    public URLParserThread(String url,Context context, Handler handler){
        super();
        this.url = url;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        Looper.prepare();

        HTMLParser htmlParser = new HTMLParser(url,context);
        try {
            String htmlString = htmlParser.CreateHTMLString();
//            System.out.println(htmlString);
            htmlParser.writeToFile(htmlString);

            String[] htmlStringArray = htmlString.split("\n");
            System.out.println(Arrays.toString(htmlStringArray));

            downloadImage("https://cdn.stocksnap.io/img-thumbs/280h/woman-car_WKVLDV0K0G.jpg");

            Looper mainThreadLooper = Looper.getMainLooper(); // --> Looper of the main/UI thread
            Message messageToSendToMainThread = Message.obtain(); // --> Create a message to send to UI thread
            messageToSendToMainThread.obj = htmlStringArray; // htmlString -> actual msg value
            messageToSendToMainThread.what = 1;
            handler.sendMessage(messageToSendToMainThread);
            Looper.loop();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void downloadImage(String target) {

        int imageLen = 0;
        int totalSoFar = 0;
        int readLen = 0;
        Bitmap bitmap = null;
        int lastPercent = 0;

        byte[] imgBytes;

        try {
                URL url = new URL(target);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                imageLen = conn.getContentLength();
                imgBytes = new byte[imageLen];

                InputStream in = url.openStream();
                BufferedInputStream bufIn = new BufferedInputStream(in, 2048);

                //should be 2048 instead of 1024 --> initially was byte[] data = new byte[1024];
                byte[] data = new byte[2048];
                while ((readLen = bufIn.read(data)) != -1) {
                    System.arraycopy(data, 0, imgBytes, totalSoFar, readLen);
                    totalSoFar += readLen;

                    int percent = Math.round(totalSoFar * 100) / imageLen;
                    if (percent - lastPercent >= 10) {
                        updateProgress(percent);
                        lastPercent = percent;
                    }
                }
                //updateProgress(100);
                bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imageLen);
                writeToFile(imgBytes,0);
                System.out.println("i AM HERE");
                //updateImage(bitmap);

            } catch(Exception e){
                e.printStackTrace();
            }
        }

    protected void updateProgress(int percent) {
        Message msg = new Message();
        msg.what = PROGRESS_UPDATE;
        msg.arg1 = percent;
        handler.sendMessage(msg);
    }

    protected void updateImage(Bitmap bitmap) {
        Message msg = new Message();
        msg.what = DOWNLOAD_COMPLETED;
        msg.obj = bitmap;
        handler.sendMessage(msg);
    }

    protected void writeToFile(byte[] imgBytes, int i){
        try{
            String filePath = "GamePhoto";
            String fileName = "photo_" + i + ".jpg";
            //get the folder directory here the file will be saved
//            System.out.println(context.getFilesDir());
            File mTargetFile = new File(context.getFilesDir(), filePath + "/" + fileName);
            System.out.println("This is the context absolute path: " + mTargetFile.getAbsolutePath());
            System.out.println("This is the context: " + context.getFilesDir());


            File parent = mTargetFile.getParentFile();
            if(!parent.exists() && !parent.mkdirs()){
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            FileOutputStream fos = new FileOutputStream(mTargetFile);
            fos.write(imgBytes);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

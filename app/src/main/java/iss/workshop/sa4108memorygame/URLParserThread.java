package iss.workshop.sa4108memorygame;

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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

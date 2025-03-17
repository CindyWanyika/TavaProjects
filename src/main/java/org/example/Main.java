package org.example;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.*;



public class Main {
    public static String getRandomVerse() throws IOException {
        String verse;
        //String surl = "https://cdn.jsdelivr.net/gh/wldeh/bible-api/bibles/en-kjv/books/john/chapters/3/verses/16.json";
        String surl="https://bible-api.com/data/web/random/NT";
        URL url=new URL(surl);


        /*curl --request GET \--url https://api.scripture.api.bible/v1/bibles \--header '76825024fe606eff15d740445562620c: test_okikJOvBiI2HlWgH4'*/
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        //check if connection is made
        int response=connection.getResponseCode();

        //200 if ok
        if(response!=200)throw new RuntimeException("ResponseCode: "+response);
        else{
            System.out.println("Connected");
            StringBuilder verseInfo=new StringBuilder();
            Scanner scanner= new Scanner(url.openStream());

            while (scanner.hasNext()){
                verseInfo.append(scanner.nextLine());
            }

            //close the scanner
            scanner.close();
            //System.out.println(verseInfo.toString());
            verse=verseInfo.toString();
        }
        Object file=JSONValue.parse(verse);

        JSONObject jsonObject=(JSONObject) file;
        JSONObject randomVerse=(JSONObject) jsonObject.get("random_verse");
        String book=(String) randomVerse.get("book");
        long chapter=(Long)randomVerse.get("chapter");
        long v=(Long)randomVerse.get("verse");
        String text=(String) randomVerse.get("text");

        String completeText=book+" "+chapter+":"+v+"\n"+text;
        return completeText;

    }
    public static void main(String[] args) throws IOException {
        System.out.println(getRandomVerse());

    }
}
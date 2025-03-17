import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JsonTest {
    public static void main(String[] args) {
        // Sample JSON string
        String jsonString = "{ \"name\": \"Cindy\", \"age\": 22 }";

        // Convert string to JSON object
        JSONObject jsonObject = new JSONObject(jsonString);

        // Print JSON values
        System.out.println("Name: " + jsonObject.getString("name"));
        System.out.println("Age: " + jsonObject.getInt("age"));
    }
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
            System.out.println(verseInfo.toString());
            verse=verseInfo.toString();
        }

        return verse;

    }
}

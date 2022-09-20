package com.example.internshipone.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
@Data
@Configuration
@Component
public class HttpConfiguration {

    private HttpURLConnection con;
    private String elasticsearchUrl;

    {
        System.out.println("Connecting to localHost............. ");
        this.elasticsearchUrl = "http://localhost:9200/";
        System.out.println("Connected to localhost: ");

    }

    private   HttpURLConnection getConnection(String requestMethod, String category, String type){
        try {
            String url = this.elasticsearchUrl + category + "/" + type;
            URL object = new URL(url);
            con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod(requestMethod);
        } catch (Exception e) {
            System.out.println("failed to connect.....");
            e.printStackTrace();
        }

        return con;
    }



    public String responseHttp(String json, String category) {
        try {
            getConnection("POST", category, "_search");
            try {

                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                wr.write(json.toString());
                wr.flush();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Out= " + e.getMessage());
            }
            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");

                }
                br.close();
            } else {
                System.out.println(con.getResponseMessage()+" Sorry and unexpected happened Check your query and Try again... ");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

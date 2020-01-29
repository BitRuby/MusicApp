package com.musicapp.serwer.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class Utils {
    private static final String token = "BQBmWM8Q1aja5GJzRuK-3QblbRW7LQ5NtX0B8FAd14nhrjyk0cHUAUec_5TAMUUlg8365cSpvBwldNQU9lejTEqzINr-Lj1c5kNIp8k-HMrAp3ao6OgDpQLRrPcnK97aTVNnRE3ADezcmOXlhQNXpBDH30AfRQyO4Oey";


    public StringBuilder getResponseFromSpotify(String search) throws IOException {

        System.out.println("Actual token: " + token);
        URL obj = new URL(search);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        String newToken = "Bearer " + token;
        connection.addRequestProperty("Authorization", newToken);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + search);
        System.out.println("Response Code : " + responseCode);

        if(responseCode == 200) {
            BufferedReader in = null;
            StringBuilder response;
            try{
                in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            } finally {
                in.close();
            }
            return response;
        } else {
            return new StringBuilder(String.format("Connection returned HTTP code: %s with message: %s",
                    responseCode, connection.getResponseMessage()));
        }
    }
}

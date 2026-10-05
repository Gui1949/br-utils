package br.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class sendPut {
    private final static String USER_AGENT = "Mozilla/5.0";

    public static String send(String url) throws Exception {
        int min = 5000; // 5 segundos em ms
        int max = 10000; // 10 segundos em ms
        int delay = min + (int)(Math.random() * (max - min + 1));
        Thread.sleep(delay);

        URL obj = new URL(url.replace(" ", ""));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String response = in.lines().collect(Collectors.joining());
            Thread.sleep(5000); // 5 segundos em ms
            return response;
        }
    }
}

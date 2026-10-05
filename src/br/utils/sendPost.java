package br.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class sendPost {
    private final static String USER_AGENT = "Mozilla/5.0";

    public static String send(String url, String data) throws Exception {
        int min = 500; // 5 segundos em ms
        int max = 1000; // 10 segundos em ms
        int delay = min + (int)(Math.random() * (max - min + 1));
        Thread.sleep(delay);

        URL obj = new URL(url.replace(" ", ""));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        con.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(data);
            wr.flush();
        }

        int responseCode = con.getResponseCode();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String response = in.lines().collect(Collectors.joining());
            Thread.sleep(500); // 5 segundos em ms
            return response;
        }
    }
}

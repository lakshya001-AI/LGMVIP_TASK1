package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Convert from( like USD ):  ");
        String convertFrom = scanner.nextLine();
        System.out.println("Convert to( like INR): ");
        String convertTo = scanner.nextLine();


        System.out.println("Quantity that has to be converted(In Numbers):  ");
        BigDecimal quantity = scanner.nextBigDecimal();

        String urlString = "https://api.exchangerate.host/latest?base=" + convertFrom.toUpperCase();




        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlString).get().build();


        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();

        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObject =jsonObject.getJSONObject("rates");
        BigDecimal rate = ratesObject.getBigDecimal(convertTo.toUpperCase());

        BigDecimal result = rate.multiply(quantity);
        System.out.println();
        System.out.println(result);

        System.out.println("Thanks For Using The Currency Converter");



    }
}
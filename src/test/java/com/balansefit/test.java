package com.balansefit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {
    public static void main(String[] args) {
        try {
            Object obj = new JSONParser().parse("");
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.get("body"));
        } catch (ParseException e) {
            System.out.println(e.getPosition());
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }

    }
}

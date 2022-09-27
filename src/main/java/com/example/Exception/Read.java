package com.example.Exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class Read {
    private static InputStream resourceAsStream;
    static {
        resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("message.properties");
    }

    public static String readData(String value) {
        String c = "";
        String co = read("E00001.00001");
        int i = co.indexOf("<");
        int s = co.indexOf(">");
        c = co.substring(0, i + 1) + value + co.substring(s);
        return c;
    }

    public static String read(String key) {
        try {
            Properties properties = new Properties();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
            properties.load(bufferedReader);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}

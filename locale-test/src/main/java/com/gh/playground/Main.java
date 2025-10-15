package com.gh.playground;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        String username = "Aladdin";
        String password = "open sesame";

        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
    }
}
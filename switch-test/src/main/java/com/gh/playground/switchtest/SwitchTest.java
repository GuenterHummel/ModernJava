package com.gh.playground.switchtest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Demo Class to test modern switch in Java
 */
public class SwitchTest {
    /**
     * main method
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        System.out.println("The different switch");

        int a = 0;

        try {
            a = SecureRandom.getInstanceStrong().nextInt(1, 4);
            System.out.println("the random number is " + a);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        switch (a) {
            case 1 -> System.out.println("Hello primary world");
            case 2 -> System.out.println("Hello secondary world");
            default -> System.out.println("Hello everywhere");
        }

    }
}

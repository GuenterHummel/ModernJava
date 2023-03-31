package com.gh.playground.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PciTestAppender {

    public static boolean findPattern (String pan, String testString) {
        final StringBuilder regexBuffer = new StringBuilder();

        for (int i = 0; i < pan.length(); i++) {
            regexBuffer.append(pan.charAt(i));
            regexBuffer.append("\\s*");
        }

        final String regex = regexBuffer.toString();
        System.out.println("regex = " + regex);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(testString);

        return matcher.find();
    }

    public static void main(String[] args) {

        final String testPAN = "5413339000001513";
        final String testMsgDefault = "Test message 5413339000001513";
        final String testMsgBlank = "Test message 5 4 1 3 3 3 9 0 0 0 0 0 1 5 1 3";

        System.out.println("-------------------------------");
        System.out.println("PAN = <" + testPAN + ">, \n" +
                "Teststring = <" + testMsgDefault + ">, \n" +
                "Match = <" + findPattern(testPAN, testMsgDefault) +">");

        System.out.println("-------------------------------");
        System.out.println("PAN = <" + testPAN + ">, \n" +
                "Teststring = <" + testMsgBlank + ">, \n" +
                "Match = <" + findPattern(testPAN, testMsgBlank) +">");
   }
}


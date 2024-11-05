package com.gh.playground.string;

import java.util.GregorianCalendar;

/**
 * Demo Class to test StringBuilder against String and StringBuffer
 */
public class TestString {
    static boolean isNotEmpty(String str) {
        return str != null && str.length() != 0;
    }

    /**
     * main method
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        testStringBufferPerformance();
        testStringBuilderPerformance();
        performGenericStringTests();

        System.out.println("Fact(30) = " + fact (30));
    }

    private static void performGenericStringTests() {
        String testStr1 = null;
        String testStr2 = "Not Empty";
        String testStr3 = "";

        System.out.println("isNotEmpty(testStr1) = " +  isNotEmpty(testStr1));
        System.out.println("isNotEmpty(testStr2) = " +  isNotEmpty(testStr2));
        System.out.println("isNotEmpty(testStr3) = " +  isNotEmpty(testStr3));
    }

    private static void testStringBufferPerformance() {
        System.out.println("Testing StringBuffer performance -- start\n");
        System.gc();
        long start=new GregorianCalendar().getTimeInMillis();
        long startMemory=Runtime.getRuntime().freeMemory();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < 10000000; i++){
            stringBuffer.append(":").append(i);
        }
        long end=new GregorianCalendar().getTimeInMillis();
        long endMemory=Runtime.getRuntime().freeMemory();
        System.out.println("Time Taken:" + (end-start));
        System.out.println("Memory used:" + (startMemory-endMemory));

        System.out.println();
        System.out.println("Testing StringBuffer performance -- end\n");
    }


    private static void testStringBuilderPerformance() {
        System.out.println("Testing StringBuilder performance -- start\n");
        System.gc();
        long start=new GregorianCalendar().getTimeInMillis();
        long startMemory=Runtime.getRuntime().freeMemory();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 10000000; i++){
            stringBuilder.append(":").append(i);
        }
        long end=new GregorianCalendar().getTimeInMillis();
        long endMemory=Runtime.getRuntime().freeMemory();
        System.out.println("Time Taken:" + (end-start));
        System.out.println("Memory used:" + (startMemory-endMemory));

        System.out.println();
        System.out.println("Testing StringBuilder performance -- end\n");
    }

    private static int fact (int n) {
        int f = 1;
        while (n > 0) {
            f = f * n;
            n = n - 1;
        }
        return f;
    }
}
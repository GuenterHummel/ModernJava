package com.gh.playground.string;

import java.util.GregorianCalendar;

public class TestString {

    private static TestString instance;

    static {
        instance = null;
    }

    public static TestString getInstance() {
        if (instance == null) {
            instance = new TestString();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.gc();
        long start=new GregorianCalendar().getTimeInMillis();
        long startMemory=Runtime.getRuntime().freeMemory();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i<10000000; i++){
            stringBuffer.append(":").append(i);
        }
        long end=new GregorianCalendar().getTimeInMillis();
        long endMemory=Runtime.getRuntime().freeMemory();
        System.out.println("Time Taken:"+(end-start));
        System.out.println("Memory used:"+(startMemory-endMemory));

        //---

        System.gc();
        start=new GregorianCalendar().getTimeInMillis();
        startMemory=Runtime.getRuntime().freeMemory();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<10000000; i++){
            stringBuilder.append(":").append(i);
        }
        end=new GregorianCalendar().getTimeInMillis();
        endMemory=Runtime.getRuntime().freeMemory();
        System.out.println("Time Taken:"+(end-start));
        System.out.println("Memory used:"+(startMemory-endMemory));

    }
}
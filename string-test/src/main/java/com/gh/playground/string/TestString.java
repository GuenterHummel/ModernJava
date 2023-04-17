package com.gh.playground.string;

import java.util.GregorianCalendar;

public class TestString {

    private static TestString instance;
    private boolean dataMissing = false;

    static boolean isNotEmpty(String str) {
        return str != null && str.length() != 0;
    }

    static {
        instance = null;
    }

    public static TestString getInstance() {
        if (instance == null) {
            instance = new TestString();
        }
        return instance;
    }

    public void setDataMissing(boolean value) {
        dataMissing = value;
    }

    public boolean getDataMissing() {
        return dataMissing;
    }

    public String toString() {
        boolean test1;

        test1 = true;
        return new StringBuilder().append("dataMissing <").append(dataMissing).append(">" ).append(test1).toString();
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

        String testStr1 = null;
        String testStr2 = "Not Empty";
        boolean result = false;

        // System.out.println("isNotEmpty(testStr1) = " +  isNotEmpty(testStr1));
        // System.out.println("isNotEmpty(testStr2) = " +  isNotEmpty(testStr2));

        System.out.println("isNotEmpty(testStr1) = " +  (testStr1 != null && testStr1.length() != 0));
        System.out.println(result);

        TestString tststr = new TestString();
        System.out.println(tststr);
        tststr.setDataMissing(true);
        System.out.println(tststr);
        tststr.setDataMissing(false);
        System.out.println(tststr);

    }
}
package com.gh.playground;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Learn how to use JSON in java
 */
public class JsonTest {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // CONVERT THE JAVA OBJECT TO JSON HERE
        ValueItem vi1 = new ValueItem("timestamp1", "feature1", 12345, "data1");
        ValueItem vi2 = new ValueItem("timestamp2", "feature2", 53421, "data2");

        ArrayList<ValueItem> vilList = new ArrayList<>();
        vilList.add(vi1);
        vilList.add(vi2);

        ValueData vd1 = new ValueData();
        vd1.setInformation(vilList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("------------------- print Object vd1 to JSON String ");

        String jsonString = gson.toJson(vd1);
        System.out.println(jsonString);

        System.out.println();
        System.out.println("------------------- JSON String vd2 to Object ");
        ValueData vd2 = gson.fromJson(jsonString, ValueData.class);

        System.out.println();
        System.out.println("------------------- print Object vd3 To String ");
        System.out.println(vd2);

        System.out.println();
        System.out.println("------------------- Print Object vd2 To JSON String ");
        System.out.println(gson.toJson(vd2));

        System.out.println();
        System.out.println("------------------- Print Object vd3 To JSON String ");
        ValueData vd3 = createValueData(ValueData.class, ValueItem.class);
        System.out.println(gson.toJson(vd3));

    }

    /**
     * createValueData method
     * @param classOfT
     * @param classOfT2
     * @return
     * @param <T>
     * @param <T2>
     */
    public static <T,T2> T createValueData(Class <T> classOfT,
                                           Class <T2> classOfT2) {

        System.out.println("Juhu");
        System.out.println(classOfT.getName());
        System.out.println(classOfT2.getName());

        try {
            return classOfT.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
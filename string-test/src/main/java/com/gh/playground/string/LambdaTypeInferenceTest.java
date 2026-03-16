package com.gh.playground.string;

import java.util.Comparator;
import java.util.List;

public class LambdaTypeInferenceTest {
    public static void main(String[] args) {
        List<String> languages =
                List.of("Java", "Kotlin", "Scala", "Groovy", "Clojure");

        languages.stream().sorted(Comparator.comparing(( name -> name.length())))
                .forEach(System.out::println);

        System.out.println();

        languages.stream().sorted(Comparator.comparing(((String name ) -> name.length())).reversed())
                .forEach(System.out::println);

        System.out.println();

        languages.stream().sorted(Comparator.comparing(String::length).reversed())
                .forEach(System.out::println);

        System.out.println();
    }
}

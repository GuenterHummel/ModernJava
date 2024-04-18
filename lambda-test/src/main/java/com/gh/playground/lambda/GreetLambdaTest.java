package com.gh.playground.lambda;

// Defining an interface whose
// implementation is given in
// the lambda expression.

interface GreetInterface {
    String greet(String recipient);
    default String greet() {
        return greet("You");
    }
}

public class GreetLambdaTest {
    public static void  main (String[] args) {
        GreetInterface greetLambda = (a) -> "Hello " + a + "!";
        // also valid: GreetInterface greetLambda = (String a) -> "Hello " + a + "!";

        System.out.println(greetLambda.greet("Folks"));
        System.out.println(greetLambda.greet());
    }
}

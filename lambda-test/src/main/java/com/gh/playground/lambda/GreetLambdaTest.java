package com.gh.playground.lambda;

// Defining an interface whose
// implementation is given in
// the lambda expression.

interface GreetInterface {
    String greet(String greetling);
    default String greet() {
        return greet("You");
    }
}

public class GreetLambdaTest {
    public static void  main (String[] args) {
        GreetInterface greetLambda = (greetling) -> "Hello " + greetling + "!";

        System.out.println(greetLambda.greet("Folks"));
        System.out.println(greetLambda.greet());
    }
}

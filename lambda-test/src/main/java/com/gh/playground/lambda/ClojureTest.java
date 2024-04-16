package com.gh.playground.lambda;

// Defining an interface whose
// implementation is given in
// the lambda expression.
// This uses the concept of
// closures
interface SalutationInterface {
    String salHello(String greetling);
    default String salHello () {
        return salHello("You");
    }
}


public class ClojureTest {
    public static void  main (String[] args) {
        SalutationInterface salObj = (greetling) -> "Hello " + greetling + "!";

        System.out.println(salObj.salHello("Folks"));
        System.out.println(salObj.salHello());
    }
}

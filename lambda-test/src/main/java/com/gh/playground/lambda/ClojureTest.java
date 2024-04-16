package com.gh.playground.lambda;

// Defining an interface whose
// implementation is given in
// the lambda expression.
// This uses the concept of
// closures
interface SalutationInterface {
    public String salHello(String greetling);
}


public class ClojureTest {
    public static void  main (String[] args) {

        SalutationInterface salObj = (greetling) -> {
            return "Hello " + greetling + "!";
        };


        System.out.println(salObj.salHello("Folks"));
    }
}

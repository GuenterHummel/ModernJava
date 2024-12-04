package com.gh.playground.lambda;

// Defining an interface whose
// implementation is given in
// the lambda expression.

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface GreetInterface {
    String greet(String recipient, String title);
    default String greet() {
        return greet("You", "");
    }
}

public class GreetLambdaTest {
    public static void  main(String[] args) {
        GreetInterface greetLambda =  (a,b) -> "Hello " + b + " "+ a + "!";
        // also valid: GreetInterface greetLambda = (String a) -> "Hello " + a + "!";

        String lambdaString = greetLambda.greet("Folks", "Common");
        System.out.println(lambdaString);
        System.out.println(greetLambda.greet());

        ActionListener actionListener = e -> {
            System.out.println("action listener activated <" + e.toString() + ">");
        };

        ChangeListener changeListener = e -> {
            System.out.println("something has changed <" + e.toString() + ">");
        };

        actionListener.actionPerformed(new ActionEvent("WOOF", 01, "Tata"));

        changeListener.stateChanged(new ChangeEvent("Hello"));
    }
}

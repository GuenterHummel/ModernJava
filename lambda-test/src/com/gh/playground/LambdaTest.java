package com.gh.playground;

public class LambdaTest {

    public static void main(String[] args) {
	    System.out.println("Lambda Test...");

	    Runnable runnableAsNormalMethod = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable as normal method");
            }
	    };
	    runnableAsNormalMethod.run();

	    Runnable runnableWithLambda = () -> System.out.println("Runnable as Lambda");
	    runnableWithLambda.run();
    }
}

package com.gh.playground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Lambda Demo Class
 */
public class LambdaTest {
    static final private Logger logger = LogManager.getLogger(LambdaTest.class.getName());
    /**
     * main method
     * @param args commandline arguments
     */
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

package com.gh.playground.lambda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Lambda Demo Class
 */
public class LambdaTest {
    final static private Logger logger = LogManager.getLogger(LambdaTest.class.getName());
    /**
     * main method
     * @param args commandline arguments
     */
    public static void main(String[] args) {
	    logger.info("Lambda Test...");

	    Runnable runnableAsNormalMethod = new Runnable() {
            @Override
            public void run() {
                logger.info("Runnable as normal method");
            }
	    };
	    runnableAsNormalMethod.run();

	    Runnable runnableWithLambda = () -> logger.info("Runnable as Lambda");

	    runnableWithLambda.run();
    }
}

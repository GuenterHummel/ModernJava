package me.schwabe.examples.javamag;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PerceptronTest {

    @Test
    public void perceptronShouldCorrectlyComputeOutput() {
        Perceptron p = new Perceptron( new float[] {1.0f, 0.0f} );
        assertTrue("Point 1 misclassified.", p.isPositive(new float[] {0.5f, 0.5f}));
        assertFalse("Point 2 misclassified.", p.isPositive(new float[] {-0.5f, 0.5f}));
        assertFalse("Point 3 misclassified.", p.isPositive(new float[] {-500f, -500f}));
    }
}
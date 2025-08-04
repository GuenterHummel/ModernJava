package com.gh.ml.perceptron;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PerceptronTest {

    @Test
    void perceptronShouldCorrectlyComputeOutput() {
        Perceptron p = new Perceptron( new float[] {1.0f, 0.0f} );
        assertTrue(p.isPositive(new float[] {0.5f, 0.5f}));
        assertFalse(p.isPositive(new float[] {-0.5f, 0.5f}));
        assertFalse(p.isPositive(new float[] {-500f, -500f}));
    }
}
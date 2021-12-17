package com.gh.ml.perceptron;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class implements a kind of perceptrons.
 */
public class Perceptron {
    final static private Logger logger = LogManager.getLogger(Perceptron.class.getName());

    private final float[] weights;

    /**
     * Constructor with weights
     * @param weights the perceptrons weights
     */
    public Perceptron(float[] weights) {
        this.weights = weights;
    }

    /**
     * Return the dimension of the perceptron
     * @return the dimension of the perceptron
     */
    public int getDim() { return weights.length; }

    /**
     * Check for positive perceptron
     * @param input the input calculated to the weight
     * @return returns <code>true</code> if positive
     * @throws IllegalArgumentException thrown if the dimensions of the input do not match the perceptron's dimensions
     */
    public boolean isPositive(float[] input) throws IllegalArgumentException {
        if (input.length != getDim()) {
            throw new IllegalArgumentException("Dimensionality mismatch.");
        }

        float sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += weights[i] * input[i];
        }
        return sum > 0f;
    }

    /**
     * Learn the perceptron
     * @param lrate the learn rate
     * @param input the inputs of the perceptron to be learned
     * @param label the label to be learned
     * @return <code>true</code> if the perceptron processed the learning input
     */
    public boolean learn(float lrate, float[] input, boolean label) {
        boolean doLearning = isPositive(input) != label;
        if (doLearning) {
            float sign = isPositive(input) ? -1.0f : 1.0f;
            for (int i=0; i<input.length; i++) {
                weights[i] += lrate * input[i] * sign;
            }
            normalizeWeightVector();
        }
        return doLearning;
    }

    private void normalizeWeightVector() {
        double norm = 0f;
        for (float weight : weights) {
            norm += weight * weight;
        }
        norm = Math.sqrt(norm);
        for (int i=0; i<weights.length; i++) {
            weights[i] /= norm;
        }
    }
}
    
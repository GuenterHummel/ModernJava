package me.schwabe.examples.javamag;

public class Perceptron {
    
private final static int DEFAULTDIM = 2;
    
private final float weights[];
    
public Perceptron() {
        this(DEFAULTDIM);
    }
    
public Perceptron(int dim) {
        this(new float[dim]);
    }
    
public Perceptron(float[] weights) {
        this.weights = weights;
    }
    
public float[] getWeights() { return weights; }
    
public int getDim() { return weights.length; };
    
public boolean isPositive(float[] input) throws IllegalArgumentException {
        if (input.length != getDim())
            throw new IllegalArgumentException("Dimensionality mismatch.");

        float sum = 0;
        for (int i=0; i<input.length; i++) { sum += weights[i] * input[i]; } return sum > 0f;
    }

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
        for (int i=0; i<weights.length; i++) {
            norm += weights[i] * weights[i];
        }
        norm = Math.sqrt(norm);
        for (int i=0; i<weights.length; i++) {
            weights[i] /= norm;
        }
    }
}
    
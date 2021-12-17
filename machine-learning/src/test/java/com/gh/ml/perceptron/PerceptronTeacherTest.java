package com.gh.ml.perceptron;

import org.junit.Test;

public class PerceptronTeacherTest {
	private static final float[][] testInputs1 = { {1.0f, 1.0f}, {1.0f, -1.0f}, {-1.0f, 1.0f}, {-1.0f, -1.0f} };
    private static final boolean[] testLabels1 = {true, true, false, false};

    private static final float[][] testInputs2 = { {1.0f, 1.0f}, {1.0f, -1.0f}, {-1.0f, 1.0f}, {-1.0f, -1.0f} };
    private static final boolean[] testLabels2 = {false, false, true, true};

    @Test
    public void perceptronShouldLearn() {
        PerceptronTeacher teacher1 = new PerceptronTeacher(testInputs1, testLabels1);
        Perceptron p1 = teacher1.createPerceptron();

        PerceptronTeacher teacher2 = new PerceptronTeacher(testInputs2, testLabels2);
        Perceptron p2 = teacher2.createPerceptron();
    }
}
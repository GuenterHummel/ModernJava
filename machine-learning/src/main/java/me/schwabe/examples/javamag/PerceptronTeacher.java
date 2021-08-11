package me.schwabe.examples.javamag;

/**
 * Class PerceptronTeacher
 */
public class PerceptronTeacher {

    private static final int MINPOINTS = 3;

    private final float[][] inputs;
    private final boolean[] labels;

    private final static float LRATE = 0.1f;

    /**
     * Constructor with parameters
     * @param inputs the perceptron inputs
     * @param labels the perceptron labels
     */
    public PerceptronTeacher(float[][] inputs, boolean[] labels) {
        if (inputs.length<MINPOINTS) throw new IllegalArgumentException("We need at least " + MINPOINTS + " one data point"); 
        this.inputs = inputs; 
        this.labels = labels; 
    }

    /**
     * Create a Perceptron
     * @return a new instance of a Perceptron
     */
    public Perceptron createPerceptron() { 
    	Perceptron p = new Perceptron(new float[] {1.0f, 0.0f}); 
    	int noOfErrors = inputs.length; 
    	
    	while (noOfErrors>0) {
            noOfErrors = 0;
            for (int i=0; i<inputs.length; i++) {
                noOfErrors += p.learn(LRATE, inputs[i], labels[i]) ? 1 : 0;
            }
            System.out.println(noOfErrors);
        }
        return p;
    }
}
package Alg;

public class LambertW {
    //  thank you to java-coding-prodigy / LambertWFunction.java for this code
    //  Slight change shifter to for loop from while loop

    public static double normalFunction(double x) {
        return x * (Math.pow(Math.E, x));
    }

    public static double derivativeOfFunction(double x) {
        return (x + 1) * Math.pow(Math.E, x);
    }

    public static double W(double x) {
        double b;
        if (x < 0)
            b = x * Math.exp(1 - Math.sqrt(2 * (Math.E * x + 1)));
        else if (x < Math.E)
            b = x / Math.E;
        else
            b = Math.log(x);
        for(int i=0; i<10; i++) {
            b -= (normalFunction(b) - x) / derivativeOfFunction(b);
        }
        return b;
    }
}

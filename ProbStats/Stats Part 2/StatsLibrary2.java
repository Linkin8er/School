//This needs Negative Binomial Distribution, and a Tchebysheff formula.
//I think everything else involves calculus

import java.math.BigInteger;

public class StatsLibrary2 {

    /**
     * This is used to find the probability that we get our rth success after y trials
     * @param r The number of sucesses we want
     * @param y The numebr of trials
     * @param p The chance of sucess
     * @param q The chance of failure
     * @return The probability
     */
    public double negativeBinomialProbabilityDistribution(int r, int y, double p, double q){
        return (findCombination(y-1, r-1)*Math.pow(p, r)*Math.pow(q,(y-r)));
    }

    /**
     * This just runs a for loop to multiply a number by each whole number less than it greater than 0
     * (factorial!)
     * @param x The number to be factored
     * @return Returns the factorial as a BigInteger (Some get quite large)
     */
    public BigInteger factorialB(int x){
        BigInteger xFactorialB = BigInteger.valueOf(1);
        for (int i = x; i > 0; i--){xFactorialB = xFactorialB.multiply(BigInteger.valueOf(i));}
        return xFactorialB;
    }

    /**
     * This just finds the number of combinations for n total objects, and r selected objects
     * @param n_TotalObjects The notal number of items in the set
     * @param r_SelectedObjects The number of items to pick out of the set
     * @return Returns number of combinations
     */
    public double findCombination(int n_TotalObjects, int r_SelectedObjects){
        double combinations = ((factorialB(n_TotalObjects).divide(factorialB(n_TotalObjects - r_SelectedObjects)))).doubleValue()/(factorialB(r_SelectedObjects)).doubleValue();
        return combinations;
    }

    /**
     * Uses Tchebyshev's theorem to find the minimum proportion of data within k standard deviations.
     * @param k The number of standard deviations from the mean.
     * @return The minimum proportion of data within k of the mean.
     */
    public double calculateTchebyshev(double k) {
        if (k < 1) {
            return 0.0; // Return 0 if k is less than 1 as it does not make sense in the context of this theorem.
        }
        return 1 - 1 / (k * k);
    }

}

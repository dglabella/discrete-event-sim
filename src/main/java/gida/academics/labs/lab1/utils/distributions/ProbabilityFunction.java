package gida.academics.labs.lab1.utils.distributions;

/**
 * Interface to specify a probability function.
 * 
 * @author Labella, Danilo Guido.
 */
public interface ProbabilityFunction {
    /**
     * Gets the probability for P(X = x).
     * 
     * @param x the little x
     * @return P(X = x)
     */
    double equalsTo(double x);

    /**
     * Gets the probability for P(X <= x).
     * 
     * @param x the little x
     * @return P(X <= x)
     */
    double lessThanOrEqualTo(double x);

    /**
     * Gets the probability for P(X >= x).
     * 
     * @param x the little x.
     * @return P(X >= x)
     */
    double greaterThanOrEqualTo(double x);

    /**
     * Gets the probability for P(x1 <= X <= x2).
     * 
     * @param x1
     * @param x2
     * @return P(x1 <= X <= x2)
     */
    double between(double x1, double x2);

    /**
     * Gets the sample x for the given cumulative probability.
     * 
     * @param cumulativeProbability The number for calculate which sample should be returned.
     * @return The sample for the given cumulative probability.
     */
    double sample(double cumulativeProbability);

    /**
     * Gets the nth moment of the distribution.
     * 
     * @param moment the moment number to be calculated
     * @return the nth moment
     */
    double nthMoment(int moment);

    /**
     * 
     * @return the expectation of the distribution
     */
    double expectation();

    /**
     * 
     * @return the variance of the distribution
     */
    double variance();

    /**
     * 
     * @return the standard deviation of the distribution
     */
    double stdDev();
}

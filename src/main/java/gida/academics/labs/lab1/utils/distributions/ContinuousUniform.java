package gida.academics.labs.lab1.utils.distributions;

import gida.academics.labs.lab1.utils.exceptions.ProbabilityFunctionException;

/**
 * This class is an implementation for an uniform distribution.
 * 
 * @author Labella, Danilo Guido.
 * 
 */
public class ContinuousUniform implements ProbabilityFunction {

    private final int MOMENTS_QUANTITY = 10;

    private final double a;
    private final double b;
    private final double delta;
    private final double inverseDelta;
    private final double expectation;
    private final double variance;
    private final double stdDev;

    private final double varianceFactor = 1d / 12d;
    private final double stdDevFactor = Math.sqrt(varianceFactor);

    private final double[] momentFactors;
    private final double[] moments;

    public ContinuousUniform(double a, double b) {
        if (b <= a)
            throw new ProbabilityFunctionException(
                    "when creating a continuous uniform distribution, b cannot be less or equal than a.");

        this.a = a;
        this.b = b;
        this.delta = b - a;
        this.inverseDelta = 1d / this.delta;
        this.expectation = (a + b) * 0.5d;
        this.variance = this.delta * this.delta * this.varianceFactor;
        this.stdDev = this.delta * this.stdDevFactor;

        this.moments = new double[this.MOMENTS_QUANTITY];
        this.momentFactors = new double[this.MOMENTS_QUANTITY];

        for (int i = 0; i < this.MOMENTS_QUANTITY; i++)
            this.momentFactors[i] = 1d / (i + 2d);

        for (int i = 0; i < this.MOMENTS_QUANTITY; i++)
            this.moments[i] = this.calculateNthMoment(i + 1);
    }

    private double calculateNthMoment(int moment) {
        return this.inverseDelta * this.momentFactors[moment - 1]
                * (Math.pow(this.b, moment + 1) - Math.pow(this.a, moment + 1));
    }

    @Override
    public double equalsTo(double x) {
        return 0d;
    }

    @Override
    public double lessThanOrEqualTo(double x) {
        return x < this.a || this.b < x ? 0d : this.inverseDelta * (x - this.a);
    }

    @Override
    public double greaterThanOrEqualTo(double x) {
        return x < this.a || this.b < x ? 0d : this.inverseDelta * (this.b - x);
    }

    @Override
    public double between(double x1, double x2) {
        if (x2 < x1)
            throw new ProbabilityFunctionException(
                    "when calculate P(x1 <= X <= x2), x2 should be greater or equal than x1, but is not.");

        return this.inverseDelta * (x2 - x1);
    }

    /**
     * {@inheritDoc}
     * <p>
     * In this case, the inverse-transform technique is being used.
     */
    @Override
    public double sample(double cumulativeProbability) {
        return this.delta * cumulativeProbability + this.a;
    }

    @Override
    public double expectation() {
        return this.expectation;
    }

    @Override
    public double nthMoment(int moment) {
        double ret = 0;
        if (0 < moment && moment <= this.MOMENTS_QUANTITY)
            ret = this.moments[moment - 1];
        else
            ret = this.calculateNthMoment(moment);

        return ret;
    }

    @Override
    public double variance() {
        return this.variance;
    }

    @Override
    public double stdDev() {
        return this.stdDev;
    }
}

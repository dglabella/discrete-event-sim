package gida.academics.labs.lab1.utils.distributions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import gida.academics.labs.lab1.utils.exceptions.ProbabilityFunctionException;

/**
 * This class is an implementation for an empirical discrete distribution.
 * 
 * @author Labella, Danilo Guido.
 * 
 */
public class EmpiricalDiscrete implements ProbabilityFunction {
    private final int MOMENTS_QUANTITY = 10;

    private final Map<Double, Double> map;
    private final List<Double> samples;
    private final List<Double> probabilities;

    private final double expectation;
    private final double variance;
    private final double stdDev;

    private final double[] moments;

    /**
     * Creates an Empirical discrete distribution.
     */
    public EmpiricalDiscrete(SortedMap<Double, Double> map) {
        this.map = map;
        this.samples = new ArrayList<>();
        this.probabilities = new ArrayList<>();

        Set<Entry<Double, Double>> entries = map.entrySet();

        for (Entry<Double, Double> e : entries) {
            this.samples.add(e.getKey());
            this.probabilities.add(e.getValue());
        }

        double sum = 0;
        for (double v : this.probabilities)
            sum += v;

        if (sum != 1d)
            throw new ProbabilityFunctionException("sum of probabilities must be 1, but is not.");

        double expectation = 0d;
        for (Entry<Double, Double> e : entries)
            expectation += (e.getKey() * e.getValue());

        this.expectation = expectation;

        this.moments = new double[this.MOMENTS_QUANTITY];
        for (int i = 0; i < this.MOMENTS_QUANTITY; i++)
            this.moments[i] = this.calculateNthMoment(i + 1, entries);

        this.variance = this.moments[1] - (this.expectation * this.expectation);
        this.stdDev = Math.sqrt(this.variance);
    }

    /**
     * Calculates the nth moment for this distribution.
     * 
     * @param moment the nth moment to be calculated.
     * @param mapEntries the map entries which is a set pairs (sample, probability).
     * @return the nth moment.
     */
    private double calculateNthMoment(int moment, Set<Entry<Double, Double>> mapEntries) {
        double ret = 0d;
        for (Entry<Double, Double> e : mapEntries)
            ret += Math.pow(e.getKey(), moment) * e.getValue();

        return ret;
    }

    @Override
    public double equalsTo(double x) {
        return this.map.containsKey(x) ? this.map.get(x) : 0d;
    }

    @Override
    public double lessThanOrEqualTo(double x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lessThanOrEqualTo'");
    }

    @Override
    public double greaterThanOrEqualTo(double x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'greaterThanOrEqualTo'");
    }

    @Override
    public double between(double x1, double x2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'between'");
    }

    @Override
    public double sample(double cumulativeProbability) {
        if (cumulativeProbability < 0)
            return 0;
        if (1 < cumulativeProbability)
            return 1;

        int i = 0;
        double cp = 0;
        while (cp < cumulativeProbability) {
            cp += this.probabilities.get(i);
            i++;
        }

        return cp > cumulativeProbability ? this.samples.get(i - 1) : this.samples.get(i);
    }

    @Override
    public double nthMoment(int moment) {
        double ret = 0;
        if (0 < moment && moment <= this.MOMENTS_QUANTITY)
            ret = this.moments[moment - 1];
        else
            ret = this.calculateNthMoment(moment, this.map.entrySet());

        return ret;
    }

    @Override
    public double expectation() {
        return this.expectation;
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

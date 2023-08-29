package gida.academics.labs.lab1.utils.generators;

import gida.academics.labs.lab1.utils.exceptions.GeneratorException;

public class LinearCongruential implements Generator<Double> {

    private final int a, c, m;
    private long lastValue;

    public LinearCongruential(int a, int c, int m) {
        if (a == 0)
            throw new GeneratorException("the constant \"a\" cannot be zero");
        if (a == m)
            throw new GeneratorException("the constant \"m\" cannot be zero");

        this.a = a;
        this.c = c;
        this.m = m;
    }

    public LinearCongruential(int a, int c, int m, long seed) {
        if (a == 0)
            throw new GeneratorException("the constant \"a\" cannot be zero");
        if (a == m)
            throw new GeneratorException("the constant \"m\" cannot be zero");

        this.a = a;
        this.c = c;
        this.m = m;
        this.lastValue = seed;
    }

    @Override
    public Double generate() {
        this.lastValue = (this.a * this.lastValue + this.c) % this.m;
        return (double) (this.lastValue / this.m);
    }
}

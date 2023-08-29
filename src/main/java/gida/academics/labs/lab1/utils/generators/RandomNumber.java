package gida.academics.labs.lab1.utils.generators;

import java.util.Random;

public class RandomNumber implements Generator<Double> {

    private Random random;

    public RandomNumber() {
        this.random = new Random(System.currentTimeMillis());
    }

    public RandomNumber(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public Double generate() {
        return this.random.nextDouble();
    }
}

package gida.academics.labs.lab1.model;

@FunctionalInterface
public interface EnqueueStrategy {

    void enqueue(Entity entity);
}

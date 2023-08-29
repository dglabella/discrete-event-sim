package gida.academics.labs.lab1.model;

import java.util.List;

@FunctionalInterface
public interface DequeueStrategy {

    Entity dequeue(List<List<Entity>> queues);
}

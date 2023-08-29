package gida.academics.labs.lab1.model;

import java.io.Serializable;
import java.util.Optional;

public interface Server extends Serializable {

    int getId();

    Entity getCurrentEntity();

    void setCurrentEntity(Entity entity);

    boolean isBusy();

    boolean watingLineIsEmpty();

    Optional<Entity> dequeue();
}

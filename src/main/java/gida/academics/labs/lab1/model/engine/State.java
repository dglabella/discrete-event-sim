package gida.academics.labs.lab1.model.engine;

public interface State {

    String getName();

    void stop(SimulationEngine engine);

    void play(SimulationEngine engine);

    void pause(SimulationEngine engine);

    void finish(SimulationEngine engine);
}

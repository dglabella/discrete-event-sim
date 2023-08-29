package gida.academics.labs.lab1.model.engine.states;

import gida.academics.labs.lab1.model.engine.SimulationEngine;
import gida.academics.labs.lab1.model.engine.State;

public class Stopped implements State {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void stop(SimulationEngine engine) {
        // do nothing
    }

    @Override
    public void play(SimulationEngine engine) {
        engine.setCurrentState(engine.getState("Playing"));
        throw new RuntimeException("engine needs to be started...");
    }

    @Override
    public void pause(SimulationEngine engine) {
        // do nothing
    }

    @Override
    public void finish(SimulationEngine engine) {
        // do nothing
    }
}

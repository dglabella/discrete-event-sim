package gida.academics.labs.lab1.model.engine.states;

import gida.academics.labs.lab1.model.engine.SimulationEngine;
import gida.academics.labs.lab1.model.engine.State;

public class Paused implements State {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void stop(SimulationEngine engine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    @Override
    public void play(SimulationEngine engine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    @Override
    public void pause(SimulationEngine engine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void finish(SimulationEngine engine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finish'");
    }
}

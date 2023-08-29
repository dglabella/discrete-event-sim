package gida.academics.labs.lab1.model.engine;

import java.util.ArrayList;
import java.util.List;
import gida.academics.labs.lab1.model.engine.states.Finished;
import gida.academics.labs.lab1.model.engine.states.Paused;
import gida.academics.labs.lab1.model.engine.states.Playing;
import gida.academics.labs.lab1.model.engine.states.Stopped;
import gida.academics.labs.lab1.utils.exceptions.SimulationEngineStateException;

/**
 * This abstract class defines the simulator engine state machine (state machine design pattern)
 */
public abstract class SimulationEngine {

    private final double simulationLenght;
    private final List<State> loadedStates;

    private State currentState;

    public SimulationEngine(double simulationLenght) {
        this.simulationLenght = simulationLenght;
        this.loadedStates = new ArrayList<State>() {
            {
                add(new Stopped());
                add(new Playing());
                add(new Paused());
                add(new Finished());
            }
        };
        this.currentState = this.loadedStates.get(0);
    }

    public SimulationEngine(double simulationLenght, List<State> states) {
        this.simulationLenght = simulationLenght;
        if (states.size() <= 0)
            throw new SimulationEngineStateException(
                    "There is no initial state for the simulation engine.");

        this.loadedStates = states;
        this.currentState = this.loadedStates.get(0);
    }

    public double getSimulationLenght() {
        return this.simulationLenght;
    }

    /**
     * Search sequentially a state with the given state name. Every State has its own name given by
     * {@link gida.simulators.labs.first.model.engine.State#getName() get name} method.
     * 
     * @param stateName The state name.
     * 
     * @return the State.
     * 
     * @throws SimulationEngineStateException if there is state with the given name.
     */
    public State getState(String stateName) {
        for (State state : loadedStates) {
            if (state.getClass().getSimpleName().equals(stateName))
                return state;
        }

        throw new SimulationEngineStateException(
                "There is no " + stateName + " state for the simulation engine.");
    }

    /**
     * Get the state located at the given index. If this object is constructed with this
     * {@link gida.simulators.labs.first.model.engine.SimulationEngine#SimulationEngine(double, List)
     * constructor}, then the state index should be known by the user.
     * 
     * @param index The list index where the state is located.
     * 
     * @return the State.
     * 
     * @throws IndexOutOfBoundsException
     */
    public State getState(int index) {
        return this.loadedStates.get(index);
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }
}

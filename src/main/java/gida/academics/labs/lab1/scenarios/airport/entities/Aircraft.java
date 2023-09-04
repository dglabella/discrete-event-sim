package gida.academics.labs.lab1.scenarios.airport.entities;

import gida.academics.labs.lab1.model.Entity;
import gida.academics.labs.lab1.model.Server;

public class Aircraft implements Entity {

    private int id;

    public Aircraft(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Server getCurrentServer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentServer'");
    }

    @Override
    public void setCurrentServer(Server server) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurrentServer'");
    }

    @Override
    public Entity build() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'build'");
    }

    @Override
    public String toString() {
        return "{\"type\":\"aircraft\",\"id\":" + this.id + "}";
    }
}

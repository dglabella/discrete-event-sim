package gida.academics.labs.lab1.model;

import java.io.Serializable;

public interface Entity extends Serializable {

    int getId();

    Server getCurrentServer();

    void setCurrentServer(Server server);

    Entity build();

}

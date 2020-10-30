package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourEvent {

    //Variable
    private Neighbour neighbour;

    //Constructor
    public AddNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    public Neighbour getNeighbour() {
        return this.neighbour;
    }
}

package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class SelectedNeighbourEvent {

    // Variable
    private Neighbour neighbour;

    //Constructor
    // for recup the neighbour when the user click
    public SelectedNeighbourEvent(Neighbour neighbour){
        this.neighbour = neighbour;
    };

    //Methods
    // get the neighbour pass in the constructor
    public Neighbour getNeighbour(){
        return this.neighbour;
    }
}

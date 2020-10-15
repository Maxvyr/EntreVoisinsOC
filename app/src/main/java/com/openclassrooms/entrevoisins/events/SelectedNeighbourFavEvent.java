package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class SelectedNeighbourFavEvent {

    //Variablr
    private Neighbour neighbour;

    //Constructor
    public SelectedNeighbourFavEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    public Neighbour getNeighbour() {
        return this.neighbour;
    }
}

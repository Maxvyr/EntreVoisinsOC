package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void changeValueFav(Neighbour neighbour) {
        //inverser la valeur lors du click
        neighbour.setFavorite(!neighbour.getFavorite());
    }

    @Override
    public List<Neighbour> getFavNeighbours() {
        List<Neighbour> neighboursFav = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getFavorite() && !neighboursFav.contains(neighbour)){
                neighboursFav.add(neighbour);
            }
        }
        return neighboursFav;
    }
}

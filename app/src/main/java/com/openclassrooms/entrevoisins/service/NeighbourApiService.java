package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour, List<Neighbour> neighboursList);

    /**
     * Check neighbour fav
     * @param neighbour
     */
    void changeValueFav(Neighbour neighbour);

    /**
     * Compare with getNeighbour
     * @return
     */
    List<Neighbour> getListFavNeighbours(Neighbour neighbourFav, List<Neighbour> neighboursFav);
}

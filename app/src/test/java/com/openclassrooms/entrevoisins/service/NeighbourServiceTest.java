package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * Delete Neighbour with success
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * Add Neighbour with success
     */
    @Test
    public void addNeighbourWithSuccess() {
        Neighbour neighbourToAdd = new Neighbour(42,"bob","","Saturne","00000000","carotte",false);
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }

    /**
     * Add Neighbour to favorite with success
     */
    @Test
    public void addToFavoriteNeighbourWithSucces() {
        Neighbour neighbourToAddFav = service.getNeighbours().get(3);
        service.addFav(neighbourToAddFav);
        Boolean isFavNeighbour = service.getNeighbours().get(3).getFavorite();
        assertTrue(isFavNeighbour);
    }

    /**
     * Neigbour addToFav list is equal to Neigbour with fav chack in list
     */
    @Test
    public void getFavoriteListNeighbourWithSucces() {
        Neighbour neighbour = service.getNeighbours().get(5);
        service.addFav(neighbour);
        List<Neighbour> neighbourListFav = service.getFavNeighbours();
        assertTrue(neighbourListFav.contains(neighbour));
    }
}

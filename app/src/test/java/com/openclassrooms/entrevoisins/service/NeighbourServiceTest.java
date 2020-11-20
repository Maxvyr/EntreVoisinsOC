package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
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
        Neighbour neighbourToAdd = new Neighbour(42,"Bob","","Saturne","00000000","carotte",false);
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }

    @Test
    public void addToFavoriteNeighbourWithSuccess() {
        Neighbour neighbourToAddFav = service.getNeighbours().get(3);
        service.changeValueFav(neighbourToAddFav);
        Boolean isFavNeighbour = service.getNeighbours().get(3).getFavorite();
        assertTrue(isFavNeighbour);
    }

    /**
     * Remove Neighbour to favorite with success
     */
    @Test
    public void removeToFavoriteNeighbourWithSuccess() {
        Neighbour neighbourFavToRemove = new Neighbour(43,"Franck","","","","",true);
        service.changeValueFav(neighbourFavToRemove);
        Boolean isNotFavNeighbour = neighbourFavToRemove.getFavorite();
        assertFalse(isNotFavNeighbour);
    }


    /**
     * Neigbour addToFav list is equal to Neigbour with fav check in list
     */
    @Test
    public void getFavoriteListNeighbourWithSuccess() {
        Neighbour neighbourToAdd = new Neighbour(43,"Franck","","","","",true);
        List<Neighbour> neigbours = new ArrayList<Neighbour>();
        List<Neighbour> neighbourListFav = service.getListFavNeighbours(neighbourToAdd, neigbours);
        assertTrue(neighbourListFav.contains(neighbourToAdd));
    }
}

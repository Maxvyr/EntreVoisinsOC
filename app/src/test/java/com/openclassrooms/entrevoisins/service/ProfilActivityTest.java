package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neigbour_profil.ProfilActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Profile Neighbour Activity
 */
@RunWith(JUnit4.class)
public class ProfilActivityTest {
    // METHODS -------------------------------------------------------------------------------------

    @Test
    public void ProfileNeighbourActivity_checkIfFavorite() {
        ProfilActivity profileActivity = new ProfilActivity();

        Neighbour goodNeighbour = new Neighbour(1, "Caroline", "http://www.xxxxx.com","","","");
        Neighbour badNeighbour = new Neighbour(2, "Silvain", "http://www.xxxxx.com","","","");
        List<Neighbour> neighbourList = Collections.singletonList(goodNeighbour);
    }

}

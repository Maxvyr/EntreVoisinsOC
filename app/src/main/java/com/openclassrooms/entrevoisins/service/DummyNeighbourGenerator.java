package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "https://source.unsplash.com/random/200x200?sig=112", "Saint-Pierre-du-Mont " + "; 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(2, "Jack", "https://source.unsplash.com/random/200x200?sig=113", "Saint-Pierre-du-Mont ; " +
                    "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, " +
                    "je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes" +
                    " tels la belote et le tarot..",false),
            new Neighbour(3, "Chloé", "https://source.unsplash.com/random/200x200?sig=114",
                    "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la " +
                    "marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de " +
                    "m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(4, "Vincent", "https://source.unsplash.com/random/200x200?sig=115", "Saint-Pierre-du-Mont ;" + " 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(5, "Elodie", "https://source.unsplash.com/random/200x200?sig=116", "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(6, "Sylvain", "https://source.unsplash.com/random/200x200?sig=117", "Saint-Pierre-du-Mont ;" + " 5km","+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(7, "Laetitia", "https://source.unsplash.com/random/200x200?sig=118", "Saint-Pierre-du-Mont " + ";" + " 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas " + "initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(8, "Dan", "https://source.unsplash.com/random/200x200?sig=119", "Saint-Pierre-du-Mont ; " +
                    "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, " +
                    "je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes" +
                    " tels la belote et le tarot..",false),
            new Neighbour(9, "Joseph", "https://source.unsplash.com/random/200x200?sig=120", "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(10, "Emma", "https://source.unsplash.com/random/200x200?sig=121",
                    "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la " +
                    "marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de " +
                    "m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(11, "Patrick", "https://source.unsplash.com/random/200x200?sig=122", "Saint-Pierre-du-Mont " + "; 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(12, "Ludovic", "https://source.unsplash.com/random/200x200?sig=123", "Saint-Pierre-du-Mont " +
                    ";" + " 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas " +
                    "initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux " +
                    "de cartes tels la belote et le tarot..",false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}

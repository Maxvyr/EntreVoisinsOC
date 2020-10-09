package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "https://source.unsplash.com/random/200x200?sig=112", "Saint-Pierre-du-Mont " + "; 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(2, "Jack", "https://source.unsplash.com/random/200x200?sig=113", "Saint-Pierre-du-Mont ; 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(3, "Chloé", "https://source.unsplash.com/random/200x200?sig=114", "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(4, "Vincent", "https://source.unsplash.com/random/200x200?sig=115", "Saint-Pierre-du-Mont ;" + " 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(5, "Elodie", "https://source.unsplash.com/random/200x200?sig=116", "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(6, "Sylvain", "https://source.unsplash.com/random/200x200?sig=117", "Saint-Pierre-du-Mont ;" + " 5km","+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(7, "Laetitia", "https://source.unsplash.com/random/200x200?sig=118", "Saint-Pierre-du-Mont " + ";" + " 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas " + "initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(8, "Dan", "https://source.unsplash.com/random/200x200?sig=119", "Saint-Pierre-du-Mont ; 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(9, "Joseph", "https://source.unsplash.com/random/200x200?sig=120", "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(10, "Emma", "https://source.unsplash.com/random/200x200?sig=121", "Saint-Pierre-du-Mont ; " + "5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(11, "Patrick", "https://source.unsplash.com/random/200x200?sig=122", "Saint-Pierre-du-Mont " + "; 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(12, "Ludovic", "https://source.unsplash.com/random/200x200?sig=123", "Saint-Pierre-du-Mont ;" + " 5km", "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..")
//            new Neighbour(1, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(2, "Jack", "https://i.pravatar.cc/150?u=a042581f4e29026704e", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(3, "Chloé", "https://i.pravatar.cc/150?u=a042581f4e29026704f", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(4, "Vincent", "https://i.pravatar.cc/150?u=a042581f4e29026704a", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(5, "Elodie", "https://i.pravatar.cc/150?u=a042581f4e29026704b", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(6, "Sylvain", "https://i.pravatar.cc/150?u=a042581f4e29026704c", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(7, "Laetitia", "https://i.pravatar.cc/150?u=a042581f4e29026703d", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(8, "Dan", "https://i.pravatar.cc/150?u=a042581f4e29026703b", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(9, "Joseph", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(10, "Emma", "https://i.pravatar.cc/150?u=a042581f4e29026706d", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(11, "Patrick", "https://i.pravatar.cc/150?u=a042581f4e29026702d", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
//            new Neighbour(12, "Ludovic", "https://i.pravatar.cc/150?u=a042581f3e39026702d", "Saint-Pierre-du-Mont ; 5km",
//                                  "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}

package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.events.SelectedNeighbourEvent;

public interface FragmentCallback {
    public void itemSelectedCallBack(SelectedNeighbourEvent event);
}

package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseFragment;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neigbour_profil.ProfilActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_add.AddNeighbourActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class NeighbourFragment extends BaseFragment {
    private List<Neighbour> mNeighbours;
    private MyNeighbourRecyclerViewAdapter adapter = null;
    private static final String TAG = "NeighbourFragment";

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        return new NeighbourFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        RecyclerView mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recoverListNeigbour();
        Log.i(TAG, "onCreateView: call");
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void recoverListNeigbour() {
        if (sharedPreferences.contains(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR)){
            String jsonListNewNeighbour = sharedPreferences.getString(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR,"");
            Type listType = new TypeToken<ArrayList<Neighbour>>(){}.getType();
            mNeighbours = gson.fromJson(jsonListNewNeighbour,listType);
        } else {
            mNeighbours = apiService.getNeighbours();
        }
        adapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        Log.d(TAG, "recoverListNeigbour: value final " + mNeighbours);
    }

    /**
     * Fired if the user clicks on a delete button
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        Log.i(TAG, "onDeleteNeighbour: " + mNeighbours.size());
        apiService.deleteNeighbour(event.neighbour);
        mNeighbours = apiService.getNeighbours();
        Log.i(TAG,
                "onDeleteNeighbour:  size list = " + mNeighbours.size() + " neighbour delete = " + event.neighbour.getName());
        stockListNeighbourSharedPrefUpadte(mNeighbours);
        adapter.notifyDataSetChanged();
    }

    private void stockListNeighbourSharedPrefUpadte(List<Neighbour> newNeighbours) {
        String listNewNeighbours = gson.toJson(newNeighbours);
        sharedPreferences.edit().putString(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR,listNewNeighbours).apply();
        Log.d(TAG, "stockListNeighbourSharedPrefUpadte");
    }

    /**
     * Add the user clicks on a delete button
     */
    @Subscribe(sticky = true)
    public void onAddNeighbour(AddNeighbourEvent event) {
        Log.i(TAG, "onAddNeighbour: " + mNeighbours.size());
//        mNeighbours.add(event.getNeighbour());
        apiService.createNeighbour(event.getNeighbour());
        mNeighbours = apiService.getNeighbours();
        Log.i(TAG,
                "onAddNeighbour:  size list = " + mNeighbours.size() + " neighbour delete = " + event.getNeighbour().getName());
        // for removing all data send with Event Bus after receive
        EventBus.getDefault().removeAllStickyEvents();
        stockListNeighbourSharedPrefUpadte(mNeighbours);
        adapter.notifyDataSetChanged();
    }

    /*
     * Listen Event to receive the click on the item list
     * and after transfer it on the ProfilActivity
     * with the method navigateTo
     */
    @Subscribe
    public void onEvent(SelectedNeighbourEvent event){
        //recover NeighbourClik
        Neighbour neighbourSelected = event.getNeighbour();
        Log.d(TAG, "neighbour: " + neighbourSelected);
        ProfilActivity.navigateTo(getActivity(),neighbourSelected);
    }

    @Subscribe(sticky = true)
    public void onEventFav(SelectedNeighbourFavEvent event) {
        Neighbour neighbourFav = event.getNeighbour();
        //iterate on the list for comparing id
        Log.d(TAG, "onEventFav reçu: " + neighbourFav);
            for (Neighbour neighbour : mNeighbours) {
                // if value id is equal set favorite for this neighbour
                if (neighbour.getId() == neighbourFav.getId()) {
                    neighbour.setFavorite(neighbourFav.getFavorite());
                    Log.i(TAG,
                            "onEventFav: neigbourFav name = " + neighbourFav.getName() + " fav " + neighbourFav.getFavorite());
                }
            }
        //maj list save
        stockListNeighbourSharedPrefUpadte(mNeighbours);
        adapter.notifyDataSetChanged();
    }
}

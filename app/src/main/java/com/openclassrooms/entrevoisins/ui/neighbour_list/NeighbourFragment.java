package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseFragment;
import com.openclassrooms.entrevoisins.di.DI;
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

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
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
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        Log.i(TAG, "onCreateView: call");
        recoverListNeigbour();
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void recoverListNeigbour() {
        if (sharedPreferences.contains(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR)){
            String jsonListNewNeighbour = sharedPreferences.getString(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR,"");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Neighbour>>(){}.getType();
            mNeighbours = gson.fromJson(jsonListNewNeighbour,listType);
        } else {
            mNeighbours = mApiService.getNeighbours();
        }
        adapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        Log.d(TAG, "recoverListNeigbour: value final " + mNeighbours);
    }

    @Override
    public void onResume() {
        recoverListNeigbour();
        super.onResume();
        Log.i(TAG, "onResume: call");
    }

    /**
     * Fired if the user clicks on a delete button
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        mNeighbours.remove(event.neighbour);
        Log.i(TAG, "onDeleteNeighbour: " + event.neighbour.getName());
        stockListNeighbourSharedPrefUpadte(mNeighbours);
        adapter.notifyDataSetChanged();
    }

    private void stockListNeighbourSharedPrefUpadte(List<Neighbour> newNeighbours) {
        Gson gson = new Gson();
        String listNewNeighbours = gson.toJson(newNeighbours);
        sharedPreferences.edit().putString(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR,listNewNeighbours).apply();
        Log.d(TAG, "stockListNeighbourSharedPrefUpadte");
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
    }
}

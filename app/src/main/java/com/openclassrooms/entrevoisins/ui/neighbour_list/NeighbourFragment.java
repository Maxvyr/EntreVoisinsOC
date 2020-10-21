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


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> newListCreateNeighbours;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter adapter = null;
    private static final String TAG = "NeighbourFragment";
//    private static final String KEY_NEIGHBOUR_FRAGMENT = "KEY_NEIGHBOUR_FRAGMENT";
    SharedPreferences sharedPreferences;

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
        //init Shared Pref
        Context context = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        Log.d(TAG, "onCreateView: call");
        addTwoList();
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        //recup list
        addTwoList();
        //plug la list a l'adapter du recycler view
        adapter.notifyDataSetChanged();
    }

    private void addTwoList() {
        mNeighbours = mApiService.getNeighbours();
        if (sharedPreferences.contains(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR)){
            String jsonListNewNeighbour = sharedPreferences.getString(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR,"");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Neighbour>>(){}.getType();
            newListCreateNeighbours = gson.fromJson(jsonListNewNeighbour,listType);
            Log.d(TAG, "addTwoList: value newList" + newListCreateNeighbours);
            mNeighbours.addAll(newListCreateNeighbours);
            adapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        } else {
            adapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        }
        Log.d(TAG, "addTwoList: value" + mNeighbours);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: call");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Stock value in shared pref
     */
    private void stockListNeighbourAdd() {
    }

    /**
     * Fired if the user clicks on a delete button
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        Log.i(TAG, "onDeleteNeighbour: " + event.neighbour.getName());
        initList();
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

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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavFragment extends BaseFragment {

    private List<Neighbour> neighboursFav;
    private static final String TAG = "FavFragment";
    public static final String KEY_LIST_FAV_NEIGHBOUR = "KEY_LIST_FAV_NEIGHBOUR";
    private MyFavNeighbourRecyclerViewAdapter adapter = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavFragment.
     */
    public static FavFragment newInstance() {
        return new FavFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        neighboursFav = new ArrayList<>();
        Log.i(TAG, "onCreate: call when " + neighboursFav);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        Context context = view.getContext();
        //variables
        RecyclerView recyclerViewFav = (RecyclerView) view;
        recyclerViewFav.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewFav.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recoverListFavNeighbour();
        Log.d(TAG, "onCreateView: neighbour fav list " + neighboursFav);
        recyclerViewFav.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    /**
     * check if list save in shared pref if yes recover them
     * call Gson Object
     * get json string in shared pref
     * convert my list of Generic in TypeToken
     * stock generic list in List showing
     */
    private void recoverListFavNeighbour() {
        String jsonListFav = "";
        if (sharedPreferences.contains(KEY_LIST_FAV_NEIGHBOUR)) {
            jsonListFav = sharedPreferences.getString(KEY_LIST_FAV_NEIGHBOUR,"");
            Log.d(TAG, "recoverListFavNeighbour: jsonList Shared pref" + jsonListFav);
        }
        updateList(jsonListFav);
    }

    private void updateList(String jsonListFav) {
        if (sharedPreferences.contains(KEY_LIST_FAV_NEIGHBOUR)) {
            //make list stock in the adapter
            Type listType = new TypeToken<ArrayList<Neighbour>>() {}.getType();
            neighboursFav = gson.fromJson(jsonListFav, listType);
            adapter = new MyFavNeighbourRecyclerViewAdapter(neighboursFav);
        } else {
            //make empty list in the adapter
            neighboursFav = new ArrayList<>();
            adapter = new MyFavNeighbourRecyclerViewAdapter(neighboursFav);
        }
    }

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        //delete user
        apiService.deleteNeighbour(event.neighbour, neighboursFav);
        Log.i(TAG, "onDeleteNeighbour: " + event.neighbour.getName());
        //save new List
        stockListFavNeighbourSharedPref(neighboursFav);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(sticky = true)
    public void onEventFav(SelectedNeighbourFavEvent event) {
        Neighbour neighbourFav = event.getNeighbour();
        Log.i(TAG, "neighbour fav receive: " + neighbourFav.getId() + " " + neighbourFav.getName());
        showList(neighbourFav);
        // for removing all data send with Event Bus after receive
        EventBus.getDefault().removeAllStickyEvents();
        String sharedPreferencesString = sharedPreferences.getString(KEY_LIST_FAV_NEIGHBOUR, "");
        Log.d(TAG, "onEventFav: sharedPref = " + sharedPreferencesString);
    }

    private void showList(Neighbour neighbourFav) {
        if(neighbourFav != null) {
                apiService.getListFavNeighbours(neighbourFav,neighboursFav);
                Log.i(TAG, "showList: " + neighbourFav);
            }
            adapter.notifyDataSetChanged();
            stockListFavNeighbourSharedPref(neighboursFav);
        }

    /**
     * Transform list NeighbourFav into a JSOn File
     * @param neighboursFav list of Neighbour add to list Fav
     */
    private void stockListFavNeighbourSharedPref(List<Neighbour> neighboursFav) {
        String listFav = gson.toJson(neighboursFav);
        sharedPreferences.edit().putString(KEY_LIST_FAV_NEIGHBOUR,listFav).apply();
    }
}
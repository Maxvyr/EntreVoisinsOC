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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {

    private Neighbour neighbourFav;
    private List<Neighbour> neighboursFav = new ArrayList<>();
    private static final String TAG = "FavFragment";
    public static final String KEY_LIST_FAV_NEIGHBOUR = "KEY_LIST_FAV_NEIGHBOUR";
    MyFavNeighbourRecyclerViewAdapter adapter = new MyFavNeighbourRecyclerViewAdapter(neighboursFav);
    SharedPreferences sharedPreferences;

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

        //init Shared Pref
        Context context = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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
        recyclerViewFav.setAdapter(adapter);
        return view;
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

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showList(Neighbour neighbourFav) {
        if(neighbourFav != null) {
            if (neighbourFav.getFavorite() && !neighboursFav.contains(neighbourFav)) {
               neighboursFav.add(neighbourFav);
               Log.i(TAG, "showList: " + neighbourFav);
            } else if (!neighbourFav.getFavorite()) {
                neighboursFav.remove(neighbourFav);
            }
            adapter.notifyDataSetChanged();
            stockListFavNeighbour(neighboursFav);
        }
    }

    /**
     * Transform list NeighbourFav into a JSOn File
     * @param neighboursFav list of Neighbour add to list Fav
     */
    private void stockListFavNeighbour(List<Neighbour> neighboursFav) {
        Gson gson = new Gson();
        String listFav = gson.toJson(neighboursFav);
        sharedPreferences.edit().putString(KEY_LIST_FAV_NEIGHBOUR,listFav).apply();
    }

    /**
     * check if list save in shared pref if yes recover them
     * call Gson Object
     * get json string in shared pref
     * convert my list of Generic in TypeToken
     * stock generic list in List showing
     */
    private void recoverListFavNeighbour() {
        if (sharedPreferences.contains(KEY_LIST_FAV_NEIGHBOUR)) {
            Gson gson = new Gson();
            String jsonListFav = sharedPreferences.getString(KEY_LIST_FAV_NEIGHBOUR,"");
            Type listType = new TypeToken<ArrayList<Neighbour>>(){}.getType();
//            neighboursFav = gson.fromJson(jsonListFav,listType);
            Log.d(TAG, "recoverListFavNeighbour: Fav !!!"  + neighboursFav);
        }
    }

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        neighboursFav.remove(event.neighbour);
        Log.i(TAG, "onDeleteNeighbour: " + event.neighbour.getName());
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
}
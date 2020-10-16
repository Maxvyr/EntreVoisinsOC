package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.utils.NeighbourFav;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {

    //variables
    private RecyclerView recyclerViewFav;
    private Neighbour neighbourFav;
    private List<Neighbour> neighboursFav = new ArrayList<>();
    private static final String TAG = "FavFragment";
    MyFavNeighbourRecyclerViewAdapter adapter = new MyFavNeighbourRecyclerViewAdapter(neighboursFav);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        Context context = view.getContext();
        recyclerViewFav = (RecyclerView) view;
        recyclerViewFav.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewFav.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
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
            if ((neighbourFav.getFavorite() && !neighboursFav.contains(neighbourFav))) {
               neighboursFav.add(neighbourFav);;
               Log.i(TAG, "showList: " + neighbourFav);
            }
            adapter.notifyDataSetChanged();
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
    }
}
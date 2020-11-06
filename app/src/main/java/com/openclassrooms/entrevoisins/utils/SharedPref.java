package com.openclassrooms.entrevoisins.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyFavNeighbourRecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPref {
    /**
     * Transform list NeighbourFav into a JSOn File
     * @param neighboursFav list of Neighbour add to list Fav
     * @param gson Gson instance
     * @param sharedPreferences SharedPreferences instance
     * @param KEY String
     */
    public void stockListFavNeighbourSharedPref(List<Neighbour> neighboursFav,
                                                      SharedPreferences sharedPreferences,
                                                 Gson gson, String KEY) {
        String listFav = gson.toJson(neighboursFav);
        sharedPreferences.edit().putString(KEY,listFav).apply();
    }

    /**
     * check if list save in shared pref if yes recover them
     * call Gson Object
     * get json string in shared pref
     * convert my list of Generic in TypeToken
     * stock generic list in List showing
     */
    public void recoverListFavNeighbour(SharedPreferences sharedPreferences,Gson gson, String KEY,
                                               List<Neighbour> neighboursFav,MyFavNeighbourRecyclerViewAdapter adapter) {
        String jsonListFav = "";
        if (sharedPreferences.contains(KEY)) {
            jsonListFav = sharedPreferences.getString(KEY,"");
            Log.d("", "recoverListFavNeighbour: jsonList Shared pref" + jsonListFav);
        }
        updateList(jsonListFav,sharedPreferences,gson,KEY,neighboursFav,adapter);
    }


    private static void updateList(String jsonListFav,SharedPreferences sharedPreferences,Gson gson, String KEY,
                                   List<Neighbour> neighboursFav,MyFavNeighbourRecyclerViewAdapter adapter) {
        if (sharedPreferences.contains(KEY)) {
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
}

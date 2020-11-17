package com.openclassrooms.entrevoisins.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment {

    protected SharedPreferences sharedPreferences;
    protected Gson gson = new Gson();
    protected NeighbourApiService apiService;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init Shared Pref
        Context context = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //init apiService
        apiService = DI.getNeighbourApiService();
    }

//    protected abstract void initListFragment();

    //On start Register to listen Event Bus
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //On stop Unregister
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}


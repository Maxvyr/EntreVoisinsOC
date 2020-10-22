package com.openclassrooms.entrevoisins.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment {

    protected SharedPreferences sharedPreferences;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init Shared Pref
        Context context = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

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


package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourEvent;
import com.openclassrooms.entrevoisins.events.openProfilActivityEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ProfilActivity extends AppCompatActivity {

    Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().post(new openProfilActivityEvent());
        setContentView(R.layout.activity_profil);
        // activate the button return on the appBar to return to previous page
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case(R.id.home):
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // For listening the Event Bus to receive Data (register)
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    // For listening thz Event Bus to receive Data (unregister)
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //listen event
    @Subscribe(sticky = true)
    public void onEvent(SelectedNeighbourEvent event){
       neighbour = event.getNeighbour();
    }
}
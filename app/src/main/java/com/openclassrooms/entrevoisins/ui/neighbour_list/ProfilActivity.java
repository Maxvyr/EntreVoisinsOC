package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfilActivity extends AppCompatActivity {

    //UI components
    @BindView(R.id.floatingActionButtonProfil)
    FloatingActionButton floatingActionButtonProfil;
    @BindView(R.id.imageViewProfil)
    ImageView imageViewProfil;
    @BindView(R.id.namenNeighbour)
    TextView nameNeighbour;
    @BindView(R.id.localisationNeighbour)
    TextView localisationNeighbour;
    @BindView(R.id.phoneNeighbour)
    TextView phoneNeighbour;
    @BindView(R.id.siteNeighbour)
    TextView siteNeighbour;
    @BindView(R.id.aboutNeighbour)
    TextView aboutNeighbour;
    @BindView(R.id.descriptionNeighbour)
    TextView descriptionNeighbour;

    //Over variables
    private static final String TAG = "ProfilActivity";
    Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    //listen event
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(SelectedNeighbourEvent event){
        Log.d(TAG, "onEvent: receive" + event);
        neighbour = event.getNeighbour();
    }
}
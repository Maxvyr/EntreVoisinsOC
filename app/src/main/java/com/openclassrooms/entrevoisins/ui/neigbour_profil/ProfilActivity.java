package com.openclassrooms.entrevoisins.ui.neigbour_profil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends AppCompatActivity {

    //UI components
    @BindView(R.id.toolbarProfil)
    Toolbar toolbarProfil;
    @BindView(R.id.floatingActionButtonProfil)
    FloatingActionButton floatingActionButtonProfil;
    @BindView(R.id.imageViewProfil)
    ImageView imageViewProfil;
    @BindView(R.id.namenNeighbourBlack)
    TextView nameNeighbourBlack;
    @BindView(R.id.namenNeighbourWhite)
    TextView nameNeighbourWhite;
    @BindView(R.id.localisationNeighbour)
    TextView localisationNeighbour;
    @BindView(R.id.phoneNeighbour)
    TextView phoneNeighbour;
    @BindView(R.id.descriptionNeighbour)
    TextView descriptionNeighbour;
    @BindView(R.id.siteNeighbour)
    TextView siteNeighbour;

    //Over variables
    private static final String TAG = "ProfilActivity";
    public static final String KEY_NEIGHBOUR = "neighbour";
    public static final String KEY_POSITION = "neighbour";

    Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);

        //retrieve Neighbour
        retrieveNeighbourFromIntent();

        //update UI
        updateUI();

        // Update Layout
        // set toolbar selected
        // activate the button return on the appBar to return to previous page
        setSupportActionBar(toolbarProfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // for change value of isFavorite and value of the drawable
        // click on FAB
        floatingActionButtonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * Methode for call this activity from the over
     * and passe value of the neighbour
     */
    public static void navigateTo(Activity activity, Neighbour neighbour) {
        Intent intent = new Intent(activity, ProfilActivity.class);
        // recover neighbour and position
        intent.putExtra(KEY_NEIGHBOUR, neighbour);
        ActivityCompat.startActivity(activity,intent, null);
    }

    //Methode to receive value from previous fragment
    private void retrieveNeighbourFromIntent() {
        // recover intent
        // to get Neighbour and position of the neighbour in the list
        Intent intent = getIntent();
        neighbour = intent.getParcelableExtra(KEY_NEIGHBOUR);
        Log.d(TAG, "value receive " + neighbour + " position dans la liste : " + neighbour.getId());
    }

    //Methode for updating the UI
    private void updateUI() {
        // Update Layout
        // Text
        nameNeighbourWhite.setText(neighbour.getName());
        nameNeighbourBlack.setText(neighbour.getName());
        localisationNeighbour.setText(neighbour.getAddress());
        phoneNeighbour.setText(neighbour.getPhoneNumber());
        descriptionNeighbour.setText(neighbour.getAboutMe());
        String name = getString(R.string.site, neighbour.getName().toLowerCase());
        siteNeighbour.setText(name);
        // Image
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.noTransformation())
                .into(this.imageViewProfil);
        //FAB color
        showStartColor();
    }

    //Method when User click on the FAB button to add favoris
    void addToFav(){
        //inverser la valeur lors du click
        neighbour.setFavorite(!neighbour.getFavorite());
        Log.d(TAG, "addToFav: " + neighbour.getId() +  " fav " + neighbour.getFavorite());
        // en fonction du click changer l'affichage
        showStartColor();
        // repasse valeur dans l'activité précédente
        EventBus.getDefault().postSticky(new SelectedNeighbourFavEvent(neighbour));
        finish();
    }

    void showStartColor() {
        Log.d(TAG, "showStartColor: " + neighbour.getFavorite());
        if (neighbour.getFavorite()) {
            floatingActionButtonProfil.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            floatingActionButtonProfil.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }
}
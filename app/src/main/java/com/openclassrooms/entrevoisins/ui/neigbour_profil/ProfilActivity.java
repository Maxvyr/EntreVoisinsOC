package com.openclassrooms.entrevoisins.ui.neigbour_profil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

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
    Neighbour neighbour;
    private static final String KEY_EXTRA_NEIGHBOUR = "neighbour";

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
    }

    /*
     * Methode for call this activity from the over
     * and passe value of the neighbour
     */
    public static void navigateTo(Activity activity, Neighbour neighbour) {
        Intent intent = new Intent(activity, ProfilActivity.class);
        intent.putExtra(KEY_EXTRA_NEIGHBOUR, neighbour);
        ActivityCompat.startActivity(activity, intent,null);
    }

    //Methode to receive value from previous fragment
    private void retrieveNeighbourFromIntent() {
        // récuperer intent créer pour recup les valeurs
        // du voisin que l'utilisateur a cliqué
        Intent intent = getIntent();
        neighbour = intent.getParcelableExtra(KEY_EXTRA_NEIGHBOUR);
        Log.d(TAG, "value receive " + neighbour);
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
    }
}
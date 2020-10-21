package com.openclassrooms.entrevoisins.ui.neighbour_add;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.toolbarAdd)
    Toolbar toolbarAdd;
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @BindView(R.id.phoneNumberLyt)
    TextInputLayout phoneInput;
    @BindView(R.id.addressLyt)
    TextInputLayout addressInput;
    @BindView(R.id.aboutMeLyt)
    TextInputLayout aboutMeInput;
    @BindView(R.id.create)
    MaterialButton addButton;

    private NeighbourApiService mApiService;
    private String mNeighbourImage;
    private static final String TAG = "AddNeighbourActivity";
    public static final String KEY_LIST_NEW_NEIGHBOUR = "KEY_LIST_NEW_NEIGHBOUR";
    List<Neighbour> newNeighbours;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //init Shared Pref
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setContentView(R.layout.activity_add_neighbour);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarAdd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();
        init();
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

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick(R.id.create)
    void addNeighbour() {
        Neighbour neighbour = new Neighbour(
                mApiService.getNeighbours().size() + 1,
                nameInput.getEditText().getText().toString(),
                mNeighbourImage,
                addressInput.getEditText().getText().toString(),
                phoneInput.getEditText().getText().toString(),
                aboutMeInput.getEditText().getText().toString(),
                false
        );
        addNeighbourToTheList(neighbour);
        Log.d(TAG, "addNeighbour: " + newNeighbours);
        finish();
    }

    /**
     * Transform list NeighbourFav into a JSOn File
     * @param newNeighbours list of Neighbour add to list Fav
     */
    private void stockListNewNeighbour(List<Neighbour> newNeighbours) {
        Gson gson = new Gson();
        String listNewNeighbours = gson.toJson(newNeighbours);
        sharedPreferences.edit().putString(KEY_LIST_NEW_NEIGHBOUR,listNewNeighbours).apply();
        Log.d(TAG, "stockListNewNeighbour: add list to SharedPref");
    }

    /**
     * Create list of new neighbour or add them to the new list
     */
    private void addNeighbourToTheList(Neighbour newNeighbour) {
        //add new neighbour to the list newNeighbours for save them
        // in Shared Pref for next usage
        if (sharedPreferences.contains(KEY_LIST_NEW_NEIGHBOUR)) {
            //if the list already exist add them to the list
            String jsonListNewNeighbour = sharedPreferences.getString(AddNeighbourActivity.KEY_LIST_NEW_NEIGHBOUR,"");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Neighbour>>(){}.getType();
            newNeighbours = gson.fromJson(jsonListNewNeighbour,listType);
            Log.i(TAG, "addNeighbourToTheList: list already exist");
        } else {
            // else create an empty list
            newNeighbours = new ArrayList<>();
            Log.i(TAG, "addNeighbourToTheList: list already exist");

        }
        //add new neighbour to the list
        newNeighbours.add(newNeighbour);
        //stock them
        stockListNewNeighbour(newNeighbours);

    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://source.unsplash.com/random/200x200?sig="+ System.currentTimeMillis();
//        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}

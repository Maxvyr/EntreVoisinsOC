package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectedNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavNeighbourRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyFavNeighbourRV";
    private final List<Neighbour> neighbours;

    public MyFavNeighbourRecyclerViewAdapter(List<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recover XML file and put them in parent
        // return view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Neighbour neighbour = neighbours.get(position);
        holder.neighbourName.setText(neighbour.getName());
        Glide.with(holder.neighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.neighbourAvatar);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: delete");
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        /*
         * make view holder clickable
         * Send data with Event Bus on the NeighbourFragment
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Click Button show Profil");
                EventBus.getDefault().post(new SelectedNeighbourEvent(neighbour));
            }
        });

    }

    @Override
    public int getItemCount() {
        return neighbours.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_list_avatar)
        public ImageView neighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView neighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton deleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

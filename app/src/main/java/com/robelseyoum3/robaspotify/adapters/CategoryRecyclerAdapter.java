package com.robelseyoum3.robaspotify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.robelseyoum3.robaspotify.R;
import com.robelseyoum3.robaspotify.models.Artist;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Artist> mArtists = new ArrayList<>();
    private Context mContext;
    private ICategorySelector mICategorySelector;

    public CategoryRecyclerAdapter(Context context, ArrayList<Artist> artists, ICategorySelector categorySelector) {
        this.mArtists = artists;
        this.mContext = context;
        this.mICategorySelector = categorySelector;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_artist_list_item, null);
        ViewHolder vh = new ViewHolder(view, mICategorySelector);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder)viewHolder).title.setText(mArtists.get(i).getTitle());

        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .setDefaultRequestOptions(options)
                .load(mArtists.get(i).getImage())
                .into(((ViewHolder)viewHolder).image);

    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView image;
        private ICategorySelector iCategorySelector;

        public ViewHolder(@NonNull View itemView, ICategorySelector categorySelector) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            iCategorySelector = categorySelector;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iCategorySelector.onArtistSelected(getAdapterPosition());
        }
    }

    public interface ICategorySelector{
        void onArtistSelected(int position);
    }

}
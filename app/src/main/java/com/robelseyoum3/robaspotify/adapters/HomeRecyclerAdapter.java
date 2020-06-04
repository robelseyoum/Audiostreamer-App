package com.robelseyoum3.robaspotify.adapters;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.robelseyoum3.robaspotify.R;

import java.util.ArrayList;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mCategories = new ArrayList<>();
    private Context mContext;
    private IHomeSelector mIHomeSelector;

    public HomeRecyclerAdapter(Context context, ArrayList<String> categories, IHomeSelector homeSelector) {
        this.mCategories = categories;
        this.mContext = context;
        this.mIHomeSelector = homeSelector;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_category_list_item, null);
        ViewHolder vh = new ViewHolder(view, mIHomeSelector);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder)viewHolder).category.setText(mCategories.get(i));

        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Drawable iconResource = null;
        switch (mCategories.get(i)){
            case "Music":{
                iconResource = ContextCompat.getDrawable(mContext, R.drawable.ic_audiotrack_white_24dp);
                break;
            }
            case "Podcasts":{
                iconResource = ContextCompat.getDrawable(mContext,R.drawable.ic_mic_white_24dp);
                break;
            }
        }
        Glide.with(mContext)
                .setDefaultRequestOptions(options)
                .load(iconResource)
                .into(((ViewHolder)viewHolder).category_icon);

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView category;
        private ImageView category_icon;
        private IHomeSelector iHomeSelector;

        public ViewHolder(@NonNull View itemView, IHomeSelector homeSelector) {
            super(itemView);
            category = itemView.findViewById(R.id.category_title);
            category_icon = itemView.findViewById(R.id.category_icon);
            iHomeSelector = homeSelector;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iHomeSelector.onCategorySelected(getAdapterPosition());
        }
    }

    public interface IHomeSelector{
        void onCategorySelected(int position);
    }
}

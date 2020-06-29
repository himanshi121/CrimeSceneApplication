package com.example.CrimeSceneApplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryItemModal> galleryItemModalList;
    private int lastPosition;

    public GalleryAdapter(List<GalleryItemModal> galleryItemModalList) {
        this.galleryItemModalList = galleryItemModalList;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {
        //String name = galleryItemModalList.get(position).getCategoryName();
        String icon = galleryItemModalList.get(position).getImage();
        //holder.setCategory(name);
        holder.setImage(icon);
        if (lastPosition <position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition=position;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView galleryImage;
       // private TextView galleryImageTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImage  = itemView.findViewById(R.id.galleryImageView);
            //categorgalleryImage = itemView.findViewById(R.id.categoryIcon);
        }

        private void setImage(String iconUrl){
            // todo: set category names from database
//            if (!iconUrl.equals("null"))
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.square_placeholder)).into(galleryImage);
        }


    }
}

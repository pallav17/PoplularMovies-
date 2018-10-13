
package com.example.pshah1.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pshah1.popularmovies.Util.Constant;
import com.squareup.picasso.Picasso;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.sampleViewHolder> {


    private Context context;
    private String[] movieTitle;
    private String[] posterUrl;



    public SampleAdapter(Context context, String[] movieTitle, String[] posterUrl) {
        this.context = context;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
    }


    @NonNull
    @Override
    public SampleAdapter.sampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);

        return new sampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleAdapter.sampleViewHolder sampleViewHolder, int position) {
        sampleViewHolder.txtView.setText(movieTitle[position]);
        Picasso.get().load(Constant.BASE_IMAGE+posterUrl[position]).into(sampleViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return movieTitle.length;
    }


    public class sampleViewHolder extends RecyclerView.ViewHolder {

        TextView txtView;
        ImageView imageView;

        public sampleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.txtView);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }

}

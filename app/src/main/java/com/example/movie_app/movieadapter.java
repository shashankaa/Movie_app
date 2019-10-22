package com.example.movie_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class movieadapter extends ArrayAdapter {
      ArrayList<Result> results=new ArrayList<Result>();
      static ArrayList<Result> newres=new ArrayList<Result>();
    public movieadapter(@NonNull Context context, int resource, @NonNull List<Result> objects) {
        super(context, resource, objects);
        results=(ArrayList<Result>) objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Result result = (Result) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_xml, parent, false);
        }

        TextView mname = (TextView) convertView.findViewById(R.id.mname);
        TextView year = (TextView) convertView.findViewById(R.id.year);
        ImageButton fav=(ImageButton) convertView.findViewById(R.id.fav);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView) ;
        Picasso.get().load("http://image.tmdb.org/t/p/w342/"+result.poster_path).into(img);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newres.contains(results.get(position))) {
                    Log.d("hereis", "came"+(results.get(position)));
                    newres.add(results.get(position));
                }
                }

            });

        mname.setText(result.Title);
        year.setText(result.releasedate);
        return convertView;

    }
    public  ArrayList<Result> arrret()
    {

        return newres;
    }
}

package com.example.movie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class movie_details extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        TextView title=findViewById(R.id.title);
        TextView overview=findViewById(R.id.overview);
        Result results=(Result)getIntent().getSerializableExtra("url");
        overview.setText(results.overview);
        ImageView img=findViewById(R.id.imageView3);
        title.setText(results.Title);

        Picasso.get().load("http://image.tmdb.org/t/p/w342/"+results.poster_path).into(img);

            Log.d("this is name",""+results.overview);


    }
}

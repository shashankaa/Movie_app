package com.example.movie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class fav_screen extends AppCompatActivity {
    ListView listView;
    ArrayList<Result> res=new ArrayList<Result>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_screen);
        listView=findViewById(R.id.listView);
        res=(ArrayList<Result>) getIntent().getExtras().getSerializable("fav");
        Toast.makeText(this, ""+res, Toast.LENGTH_SHORT).show();
        movieadapter mov=new movieadapter(fav_screen.this,R.layout.movie_xml,res);
        listView.setAdapter(mov);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(fav_screen.this,movie_details.class);
                Result result=new Result(res.get(position).poster_path,res.get(position).Title,res.get(position).overview,res.get(position).releasedate,res.get(position).voteaverage,res.get(position).popularity);
                intent.putExtra("url",(Serializable) result);
                intent.putExtra("name",(Serializable) res.get(position));

                startActivity(intent);
                finish();

            }
        });

    }

}

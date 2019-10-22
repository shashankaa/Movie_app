package com.example.movie_app;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String urlparam;
    EditText find;
    String newparam="";
    String url;
    ListView listview;
    movieadapter movie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=findViewById(R.id.listView);





        findViewById(R.id.Search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find =(EditText) findViewById(R.id.find);
                urlparam=find.getText().toString();
                Log.d("web",""+urlparam);
                urlparam.split(" ");
                for(String str:urlparam.split(" "))
                {
                    newparam=newparam+"+"+str;
                     url="https://api.themoviedb.org/3/search/movie?query="+newparam+"&api_key=58a6f7342f33c945a376e7baacc557f5&page=1";

                }
                new GetDataAsync().execute(url);



                newparam="";
                Log.d("this is",""+url);
            }
        });

        String[] str = {"","fav selected"};
        Spinner dropdown = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, str);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position==1) {
                    Intent intent = new Intent(MainActivity.this, fav_screen.class);
                    intent.putExtra("fav", movie.arrret());
                    startActivity(intent);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<Result>> implements Serializable {
        AlertDialog dialog;
        AlertDialog.Builder builder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected ArrayList<Result> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Result> results = new ArrayList<Result>();
            try {
                URL url = new URL(params[0]);
                Log.d("url", "" + url);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                Log.d("this",""+connection.getResponseCode());
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                    Log.d("demos", "null result"+json);
                    JSONObject root = new JSONObject(json);

                    JSONArray Sources = root.getJSONArray("results");

                    for (int i = 0; i < Sources.length(); i++) {
                        Result result = new Result();
                        result.Title = Sources.getJSONObject(i).getString("title");
                        result.poster_path = Sources.getJSONObject(i).getString("poster_path");
                        result.overview = Sources.getJSONObject(i).getString("overview");
                        result.releasedate = Sources.getJSONObject(i).getString("release_date");
                        result.voteaverage = Sources.getJSONObject(i).getString("vote_average");


                       results.add(result);

                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return results;
        }

        protected void onPostExecute(final ArrayList<Result> results) {
            if (!results.isEmpty()) {
                movie = new movieadapter(MainActivity.this, R.layout.movie_xml,results);
                listview.setAdapter(movie);
                movie.notifyDataSetChanged();






                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,   View view, int position, long id) {
                        Log.d("this ret",""+movie.arrret());
                       Intent intent=new Intent(MainActivity.this,movie_details.class);
                        Result result=new Result(results.get(position).poster_path,results.get(position).Title,results.get(position).overview,results.get(position).releasedate,results.get(position).voteaverage);
                        intent.putExtra("url",(Serializable) result);
                        intent.putExtra("name",(Serializable) results.get(position));

startActivity(intent);

                    }
                });


            } else {
                Log.d("demo", "null result");
            }
        }

    }

}



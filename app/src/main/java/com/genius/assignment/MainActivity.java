package com.genius.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.assignment.adapter.ItemAdapter;
import com.genius.assignment.model.Items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Items> spinner_title;
    ArrayList<String> bannerSpiner;
    ArrayList<String> brandSpiner;

    ItemAdapter itemAdapter;

    RequestQueue requestQueue;
    String url="http://3.7.82.87/api/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue= Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject data = jsonObject.getJSONObject("output");
                    spinner_title = new ArrayList<>();

                    JSONArray jsonArray = data.getJSONArray("banner");
                    bannerSpiner = new ArrayList<>();

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        String banner_title = obj.getString("title");
                        bannerSpiner.add(banner_title);
                    }

                    spinner_title.add(new Items("Banner",bannerSpiner));

                    JSONArray jsonArray2 = data.getJSONArray("brands");
                    brandSpiner = new ArrayList<>();

                    for(int i=0;i<jsonArray2.length();i++)
                    {
                        JSONObject obj = jsonArray2.getJSONObject(i);

                        String banner_title = obj.getString("name");
                        brandSpiner.add(banner_title);
                    }

                    spinner_title.add(new Items("Brands",brandSpiner));

                    itemAdapter = new ItemAdapter(MainActivity.this, spinner_title);
                    recyclerView.setAdapter(itemAdapter);
                    itemAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }
}

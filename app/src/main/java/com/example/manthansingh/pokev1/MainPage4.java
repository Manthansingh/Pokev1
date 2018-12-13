package com.example.manthansingh.pokev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage4 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PokeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList <String> images=new ArrayList<>();
    ArrayList<String> imagename= new ArrayList<>();
    private RequestQueue requestqueue;
    private StringRequest request;
    String searchby,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page4);
        Bundle bundle=getIntent().getExtras();
        searchby=bundle.getString("searchby");
        search=bundle.getString("search");
        mRecyclerView = (RecyclerView) findViewById(R.id.myrecycler);

        mRecyclerView.setHasFixedSize(true);
        requestqueue= Volley.newRequestQueue(this);

        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        data();


    }

    private void data() {
        final String localhost="http://192.168.43.182/";
        String URL=localhost+searchby;
         request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsarray = null;
                try {
                    jsarray = new JSONArray(response);
                    int length=jsarray.length();
                    for (int i=0;i<length;i++)
                    {
                        JSONObject jsonObject= jsarray.getJSONObject(i);
                        String image=localhost+jsonObject.getString("pkimage");
                        String name=jsonObject.getString("pkname");

                        imagename.add(name);
                        images.add(image);
                    }
                    mAdapter = new PokeAdapter(MainPage4.this,images,imagename);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashmap=new HashMap<String,String>();
                hashmap.put("data",search);
                return hashmap;
            }
        };
        requestqueue.add(request);
    }
}

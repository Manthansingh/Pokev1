package com.example.manthansingh.pokev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainPage3 extends AppCompatActivity {

    public static final String TAG = MainPage3.class.getSimpleName();
    final String localhost="http://192.168.43.182/";
    private static final String REQ_TAG = "REQUEST";
    private RequestQueue requestqueue;
    TextView iddisplay, namedisplay, typedisplay, basedisplay, statdisplay, hpdisplay, cpdisplay, attackdisplay, defensedisplay, staminadisplay;
    ImageView imageviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page3);

        initViews();

        Bundle bundle = getIntent().getExtras();
        String searchby = bundle.getString("searchby");
        String search = bundle.getString("search");
        requestqueue= Volley.newRequestQueue(this);

        //requestqueue = RequestQueueSingleton.getInstance(this).getRequestQueue();
        callData(searchby, search);
    }

    private void initViews() {
        iddisplay = findViewById(R.id.iddisplay);
        namedisplay = findViewById(R.id.namedisplay);
        typedisplay = findViewById(R.id.typedisplay);
        basedisplay = findViewById(R.id.basedisplay);
        statdisplay = findViewById(R.id.statdisplay);
        hpdisplay = findViewById(R.id.hpdisplay);
        cpdisplay = findViewById(R.id.cpdisplay);
        attackdisplay = findViewById(R.id.attackdisplay);
        defensedisplay = findViewById(R.id.defensedisplay);
        staminadisplay = findViewById(R.id.staminadisplay);
        imageviewer =findViewById(R.id.imageviewer);
    }

    private void callData(String searchby, final String search) {
        String url = localhost+ searchby;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: String Response : "+ response);
                        try {
                            handleResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: Error getting response" + error.networkResponse);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> hashmap = new HashMap<String, String>();
                hashmap.put("data", search);
                return hashmap;
            }
        };

        stringRequest.setTag(REQ_TAG);
        requestqueue.add(stringRequest);
    }

    private void handleResponse(String response) throws JSONException {
        JSONArray jsarray = new JSONArray(response);
        int length = jsarray.length();
        for (int i = 0; i < 1; i++) {
            JSONObject jsonObject = jsarray.getJSONObject(i);

            String id = jsonObject.getString("pkid");
            String name = jsonObject.getString("pkname");
            String gen = jsonObject.getString("gen");
            String evolution = jsonObject.getString("evchart");
            String evlcost = jsonObject.getString("evcost");
            String cp = jsonObject.getString("maxcp");
            String hp = jsonObject.getString("maxhp");
            String attack = jsonObject.getString("attack");
            String defence = jsonObject.getString("defense");
            String stamina = jsonObject.getString("stamina");
            String catchrate = jsonObject.getString("catchrate");
            String type = jsonObject.getString("ptype");
            String shiny = jsonObject.getString("shiny");
            String category = jsonObject.getString("category");
            String moveset = jsonObject.getString("moveset");
            String weakness = jsonObject.getString("weakness");
            String buddy = jsonObject.getString("buddy");
            String image=localhost+jsonObject.getString("pkimage");

            if (length == 2) {
                type = type + "," + jsarray.getJSONObject(length - 1).getString("ptype");
            }
            iddisplay.setText(id);
            namedisplay.setText(name);
            defensedisplay.setText(defence);
            attackdisplay.setText(attack);
            staminadisplay.setText(stamina);
            hpdisplay.setText(hp);
            cpdisplay.setText(cp);
            //imageviewer.setImageBitmap(BitmapFactory.decodeFile(image));
            Glide.with(this.getBaseContext()).asBitmap().load(image).into(imageviewer);
            basedisplay.setText("GENERATION : "+gen+"\n"+"BUDDY DISTANCE : "+buddy+"\n"+"CATEGORY : "+category+"\n"+"SHINY FOUND : "+shiny+"\n"+"EVOLUTION #CANDY : "+ evlcost);
            statdisplay.setText("WILD CATCHRATE(%) : "+catchrate+"\n"+"WEAK AGAINST : "+weakness+"\n"+"EVOLUTION NAME : "+evolution+"\n"+"BEST MOVESETS : "+moveset);
            typedisplay.setText(type);
        }
    }
}

package com.example.manthansingh.pokev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.HashMap;
import java.util.Map;

public class MainPage2 extends AppCompatActivity {

    EditText pokename, pokeid, poketype;
    String pokeName, pokeId, pokeType, searchby, search;
    Button b1;
    private RequestQueue requestqueue;
    private StringRequest stringrequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page2);
        pokename = (EditText) findViewById(R.id.nameLayer);
        pokeid = (EditText) findViewById(R.id.idLayer);
        poketype = (EditText) findViewById(R.id.typeLayer);
        b1 = (Button) findViewById(R.id.button);

        // requestqueue= Volley.newRequestQueue(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("name1", "name1");
                pokeName = pokename.getText().toString();
                pokeId = pokeid.getText().toString();
                pokeType = poketype.getText().toString();
                if (pokeName.length() != 0) {
                    searchby = "namesearch.php";
                    search = pokeName;
                    opPage3();
                    Log.d("name2", "name2");
                } else if (pokeId.length() != 0) {
                    search = pokeId;
                    searchby = "idsearch.php";
                    opPage3();
                }

                else if (pokeType.length() != 0) {
                    searchby = "typesearch.php";
                    search = pokeType;
                    onPage4();
                }
                // callData(searchby);
            }

            ;
        });
    }

    private void onPage4() {
        Intent intent2 = new Intent(this, MainPage4.class);
        intent2.putExtra("searchby", searchby);
        intent2.putExtra("search", search);
        startActivity(intent2);
    }

    public void opPage3() {
        Intent inten1 = new Intent(MainPage2.this, MainPage3.class);
        inten1.putExtra("searchby", searchby);
        inten1.putExtra("search", search);
        startActivity(inten1);
    }


    /*private void callData(String searchby) {

        String url="http://192.168.0.102/"+searchby;
        stringrequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsarray =new JSONArray(response);
                    for (int i=0;i<jsarray.length();i++){
                        JSONObject jsonObject= jsarray.getJSONObject(i);

                        String pkname
                    }
                }
                catch(JSONException je)
                {
                    je.printStackTrace();
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
                hashmap.put("pokename",pokename.getText().toString());
                hashmap.put("pokeid",pokeid.getText().toString());
                hashmap.put("poketype",poketype.getText().toString());
                return hashmap;
            }
        };
        requestqueue.add(stringrequest);
    }*/
}
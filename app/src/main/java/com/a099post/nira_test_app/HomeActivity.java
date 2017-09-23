package com.a099post.nira_test_app;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class HomeActivity extends FragmentActivity implements BlankFragment.OnFragmentInteractionListener{
    private FloatingActionButton fab;
    cAdaptor mAdapter;
    RecyclerView recyclerView;
    private String JSON_STRING = null;
    String url = "http://192.168.42.55:3000/api/company";
    List<Companies> list = new ArrayList<Companies>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new LoadingTask(getApplicationContext()).execute(url);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment bf = new BlankFragment();
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                Log.v("here","fragment added");
                ft.add(R.id.container,bf);
                ft.commit();


            }
        });
//        new LoadingTask(this)
;        RecyclerView.LayoutManager LM = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LM);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.setAdapter(mAdapter);
    //new LoadingTask(getApplicationContext()).execute(url);


    }

    public class LoadingTask extends AsyncTask<String, String,String>{
private Context  mcontext;
   public  LoadingTask(Context mContext){

       this.mcontext = mContext;
   }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSON_STRING =s;
            LoadData(JSON_STRING);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            BufferedReader br = null;

            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //conn.connect();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String Output;
                while((Output = br.readLine()) != null){
                    sb.append(Output);

                }

conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException n){
                n.printStackTrace();

            }
Log.d("dataString",sb.toString());
            return sb.toString();
        }
    }

    public void LoadData(String s){
        try {
            JSONObject mObject = new JSONObject(s);
            JSONArray mArray = mObject.getJSONArray("message");
            for(int i =0; i< mArray.length()-1;i++){
                JSONObject jO = mArray.getJSONObject(i);

               String name = jO.getString("name");
                String sector = jO.getString("sector");
                String score = jO.getString("score");

                Companies mCompanies = new Companies();
                mCompanies.setName(name);
                mCompanies.setScore(parseInt(score));
                mCompanies.setSector(sector);
                list.add(mCompanies);






            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
     mAdapter = new cAdaptor(list);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

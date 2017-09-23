package com.a099post.nira_test_app;

import android.content.Context;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by DESKTOP on 18-09-2017.
 */

public class LogibTask extends AsyncTask<String, String, String> {

    private Context mContext;

    public LogibTask(Context context) {
        super();
        this.mContext = context;

    }

    @Override
    protected void onPostExecute(String s) {
        //  Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        if (s.equalsIgnoreCase("code already used")) {
            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

        }
        if (s.equalsIgnoreCase("fail")) {
            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase("HTTPOK")) {
            Intent i = new Intent(mContext.getApplicationContext(), HomeActivity.class);
            mContext.startActivity(i);

        }else if(s.equalsIgnoreCase("HTTPGOOD")){
            Toast.makeText(mContext, "YOU ARE REGIETERD TO THIS COMPANY", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected String doInBackground(String... urls) {

        BufferedReader br = null;
        String s = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urls[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            ///  conn.connect();
            conn.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String Output;
            while ((Output = br.readLine()) != null) {
                sb.append(Output);


            }

            //  conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.v("the loging string", sb.toString());
            JSONObject jb = new JSONObject(sb.toString());
            s = jb.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("the loging string", s);

        return s;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    public String sendPostRequest(String requestURL,
                                  HashMap<String, String> postDataParams) {
        //Creating a URL
        URL url;

        //StringBuilder object to store the message retrieved from the server
        StringBuilder sb = new StringBuilder();
        try {
            //Initializing Url
            url = new URL(requestURL + getPostDataString(postDataParams));
            Log.e("URL", postDataParams.toString());
            //Creating an httmlurl connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Reading server response
                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("response", sb.toString());

        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                result.append("?");
                first = false;
            } else
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
            //result.append(",");
        }
        Log.v("result data Type", result.toString());
        return result.toString();
    }
}


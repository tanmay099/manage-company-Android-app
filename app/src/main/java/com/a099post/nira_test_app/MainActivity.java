package com.a099post.nira_test_app;

import android.content.AsyncTaskLoader;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //   public static final String LOGIN_URL = "http://192.168.42.151:3000/api/login?email=tanmay09.sharma@gmail.com&password=tanmay1234&referral=NIRA01";
    private EditText username, pasword, referral;
    public static final String PREF_NAME = "MyPreferanceFile";
    private Button login;
    private String user, pass;
    public SharedPreferences preferences;
    LogibTask LT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        pasword = (EditText) findViewById(R.id.password);
        referral = (EditText) findViewById(R.id.refferal);
        LT = new LogibTask(getApplicationContext());
        SharedPreferences perferance = getSharedPreferences(PREF_NAME, 0);


        final SharedPreferences.Editor editor = perferance.edit();
        editor.remove("username");
        //user login button click event
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = "https://192.168.42.55:3000/api/login?email=" + username.getText().toString() + "&password=" + pasword.getText().toString() + "&rcode=" + referral.getText().toString().toUpperCase();
                editor.putString("username", "Keven12@gmail.com");
                editor.putString("password", "xtw8tilVps_yfXm");
                editor.commit();

                Log.v("url value", url);
                LT.execute(url);
            }
        });


    }


}




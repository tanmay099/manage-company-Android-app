package com.a099post.nira_test_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView name = (TextView)findViewById(R.id.dName);
        TextView score = (TextView)findViewById(R.id.dScore);
        TextView sector = (TextView)findViewById(R.id.dSector);
        name.setText(getIntent().getStringExtra("name"));
        score.setText(getIntent().getStringExtra("score"));
        sector.setText(getIntent().getStringExtra("sector"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Toast.makeText(this,"menu item clicked",Toast.LENGTH_LONG).show();
                startActivity(new Intent(detailActivity.this,About.class));
                return true;
            default :
                return super.onOptionsItemSelected(item);



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }
}

package com.a099post.nira_test_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class cAdaptor extends RecyclerView.Adapter<cAdaptor.MyViewHolder> implements AdapterView.OnItemClickListener {
    public Context mContext;
    public HomeActivity HM;
    public String url = new String();

    public cAdaptor(HomeActivity hm) {
        this.HM = hm;

    }

    public cAdaptor(Context context) {
        this.mContext = context;
    }

    public List<Companies> companies;

    public cAdaptor(List<Companies> complist) {

        this.companies = complist;
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemview, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, score, sector;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            score = (TextView) itemView.findViewById(R.id.scores);
            sector = (TextView) itemView.findViewById(R.id.sector);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Companies company = companies.get(position);
        holder.name.setText(company.getName());
        int score = company.getScore();
        String SCORE = Integer.toString(score);
        holder.score.setText(SCORE);
        holder.sector.setText(company.getSector());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()) {
                    SharedPreferences preferences = view.getContext().getSharedPreferences(MainActivity.PREF_NAME, 0);
                    String userdata = preferences.getString("username", "Keven12@gmail.com");
                    url = "http://192.168.42.55:3000/api/user?company=" + companies.get(position).getName() + "&user=" + userdata;
                    new LogibTask(mContext).execute(url);

                    Toast.makeText(view.getContext(), "is checked", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(view.getContext(), detailActivity.class);
                    i.putExtra("name", companies.get(position).getName());
                    int score = companies.get(position).getScore();
                    String SCORE = Integer.toString(score);
                    i.putExtra("score", SCORE);
                    i.putExtra("sector", companies.get(position).getSector());
                    view.getContext().startActivity(i);
                } else {
                    Toast.makeText(view.getContext(), "is NOT checked", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}

package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_articles =
            {
                    {"Walking Daily" ,"","","","Click More Details"},
                    {"Home care of COVID-19" ,"","","","Click More Details"},
                    {"Stop Smoking" ,"","","","Click More Details"},
                    {"Menstrual Cramps" ,"","","","Click More Details"},
                    {"Healthy Gut" ,"","","","Click More Details"}
            };
   private int[] images = {
           R.drawable.health1,
           R.drawable.health2,
           R.drawable.health3,
           R.drawable.health4,
           R.drawable.health5
   };
   HashMap<String,String> item;
   ArrayList list;
   SimpleAdapter sa;

   Button btn;
   ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        btn = findViewById(R.id.b1);
        lst = findViewById(R.id.listHA);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < health_articles.length; i++) {
            item = new HashMap<String,String>();
            item.put("line1", health_articles[i][0]);
            item.put("line2", health_articles[i][1]);
            item.put("line3", health_articles[i][2]);
            item.put("line4", health_articles[i][3]);
            item.put("line5", health_articles[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.multiline,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.l1, R.id.l2, R.id.l3, R.id.l4, R.id.l5});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(HealthArticlesActivity.this,HADetailsActivity.class);
                it.putExtra("text1" ,health_articles[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });
    }
}
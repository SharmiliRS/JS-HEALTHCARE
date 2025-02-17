package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {
    private String[][] order_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.b1);
        lst = findViewById(R.id.listOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);

        ArrayList dbData = db.getorderdata(username);

        order_details = new String[dbData.size()][];
        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[0];
            order_details[i][1] = strData[1] ;
            order_details[i][3] = "Del: " + strData[4] + " " + strData[5];
            order_details[i][2] = "Rs. "+strData[6];
            order_details[i][4] = strData[7];

        }
        list = new ArrayList();
        for (int i = 0; i < order_details.length; i++) {
            item = new HashMap<String,String>();
            item.put("line1", order_details[i][0]);
            item.put("line2", order_details[i][1]);
            item.put("line3", order_details[i][2]);
            item.put("line4", order_details[i][3]);
            item.put("line5", order_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.multiline,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.l1, R.id.l2, R.id.l3, R.id.l4, R.id.l5});
        lst.setAdapter(sa);
    }
}
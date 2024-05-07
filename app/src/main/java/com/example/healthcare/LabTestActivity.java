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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Package 1 : Full Body Checkup","","","","999"},
            {"Package 2 : Blood Glucose Fasting","","","","299"},
            {"Package 3 : COVID - 19 Antibody - IgB","","","","899"},
            {"Package 4 : Thyroid Check","","","","499"},
            {"Package 5 : Immunity Check","","","","699"}
    };

    private String[] package_details = {
            "Full Body Checkup\n\n" +
                    "1.Complete Hemogram\n"+
                    "2.HbA1c\n"+
                    "3.Iron Studies\n"+
                    "4.Kidney Function Test\n"+
                    "5.Liver Function Test\n"+
                    "6.Lipid Profile",
            "Blood Glucose Fasting",
            "COVID - 19 Antibody - IgB",
            "Thyroid Check\n\n"+
                    "Thyroid Profile-Total (T3,T4 & TSH Ultra-sensitive)",
            "Immunity Check\n\n"+
                    "CRP(C Reactive Protein) Quantiative , Serum\n"+
                    "1.Vitamin D Total-25 Hydroxy\n"+
                    "2.Iron Studies\n"+
                    "3.Kidney Function Test\n"+
                    "4.Liver Function Test\n"+
                    "5.Lipid Profile"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnback,btncart;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnback = (Button) findViewById(R.id.b1);
        btncart = (Button) findViewById(R.id.b2);
        lv = (ListView) findViewById(R.id.list);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i = 0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+packages[i][4]+"/-");
            list.add(item);
        }
        sa =  new SimpleAdapter(this,list,R.layout.multiline,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.l1,R.id.l2,R.id.l3,R.id.l4,R.id.l5});
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetails.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, cartactivity.class));
            }
        });

    }
}
package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edadd,edcon,edpin;
    Button book;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname=(EditText) findViewById(R.id.etfn);
        edadd=(EditText)findViewById(R.id.etadd);
        edcon=(EditText)findViewById(R.id.etconnum);
        edpin=(EditText)findViewById(R.id.etpin);
        book =(Button) findViewById(R.id.btnbook);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                db.addorder(username,edname.getText().toString(),edadd.getText().toString(),edcon.getText().toString(),Integer.parseInt(edpin.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removecart(username,"lab");
                Toast.makeText(LabTestBookActivity.this, "Booked Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });

    }
}
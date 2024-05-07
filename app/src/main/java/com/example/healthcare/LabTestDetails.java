package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetails extends AppCompatActivity {
    TextView tvpackagename,tvtotalcost;
    EditText ed;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvpackagename = findViewById(R.id.t1);
        tvtotalcost = findViewById(R.id.t4);
        ed = findViewById(R.id.et1);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        ed.setKeyListener(null);

        Intent intent = getIntent();
        tvpackagename.setText(intent.getStringExtra("text1"));
        ed.setText(intent.getStringExtra("text2"));
        tvtotalcost.setText("Total cost: "+ intent.getStringExtra("text3") + "/-");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetails.this, LabTestActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product=tvpackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addcart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetails.this,LabTestActivity.class));
                }
            }
        });

    }

}
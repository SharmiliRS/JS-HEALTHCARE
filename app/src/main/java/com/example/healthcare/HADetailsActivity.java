package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HADetailsActivity extends AppCompatActivity {
    TextView tv;
    ImageView img;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadetails);

        btn =findViewById(R.id.b1);
        img =findViewById(R.id.imageHD);
        tv =findViewById(R.id.t3);

        Intent intent=getIntent();
        tv.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HADetailsActivity.this,HealthArticlesActivity.class));
            }
        });
    }
}
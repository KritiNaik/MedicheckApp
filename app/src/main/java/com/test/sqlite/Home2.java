package com.test.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home2 extends AppCompatActivity {
Button btnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        btnn = (Button) findViewById(R.id.button);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eyeIntent = getPackageManager().getLaunchIntentForPackage("com.test.app2");
                startActivity(eyeIntent);
            }
        });
    }
    }

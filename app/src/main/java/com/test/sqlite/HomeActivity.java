package com.test.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    TextView mTextViewCalendar;
    TextView mTextViewEye;
    TextView mTextViewWatch;
    TextView mTextViewLogout;
    TextView mTextViewRoutine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextViewCalendar = (TextView) findViewById(R.id.textViewhome);
        mTextViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(HomeActivity.this, Calendar.class);
                startActivity(LoginIntent);
            }
        });
        mTextViewEye = (TextView) findViewById(R.id.textViewhome2);
        mTextViewEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(HomeActivity.this, Home2.class);
                startActivity(LoginIntent);
            }
        });
        mTextViewWatch = (TextView) findViewById(R.id.textView3);
        mTextViewWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(HomeActivity.this, MainActivity2.class);
                startActivity(LoginIntent);
            }
        });

        mTextViewLogout = (TextView) findViewById(R.id.textView6);
        mTextViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(LoginIntent);
                finish();
                Toast.makeText(HomeActivity.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
            }
        });
        mTextViewRoutine = (TextView) findViewById(R.id.textView4);
        mTextViewRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eyeIntent = getPackageManager().getLaunchIntentForPackage("com.test.app32");
                startActivity(eyeIntent);
            }
        });

    }
}
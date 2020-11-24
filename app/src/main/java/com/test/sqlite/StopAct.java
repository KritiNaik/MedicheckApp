package com.test.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopAct extends AppCompatActivity {
    Button btnStart, btnstop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        btnStart=findViewById(R.id.btnstart);
        btnstop=findViewById(R.id.btnstop);
        icanchor=findViewById(R.id.icanchor);
        timerHere=findViewById(R.id.timerHere);


        btnstop.setAlpha(0);

        roundingalone= AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnStart.animate().alpha(0).setDuration(300).start();

                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
            }
        });
    }
}
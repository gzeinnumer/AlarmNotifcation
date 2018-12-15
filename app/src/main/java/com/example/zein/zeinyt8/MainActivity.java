package com.example.zein.zeinyt8;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton rbNotiv, rbToast;
    Button btnOne, btnRepeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbNotiv = (RadioButton) findViewById(R.id.rbNotiv);
        rbToast = (RadioButton) findViewById(R.id.rbToast);

        btnOne = (Button) findViewById(R.id.btnOne);
        btnRepeat = (Button) findViewById(R.id.btnRepeat);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbNotiv.isChecked())
                    startAlarm(true, false);
                else
                    startAlarm(false,false);
            }
        });
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbNotiv.isChecked())
                    startAlarm(true, false);
                else
                    startAlarm(false,false);
            }
        });
    }

    private void startAlarm(boolean isNotivication, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        if(!isNotivication){
            myIntent = new Intent(MainActivity.this, AlarmToastReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0,myIntent,0);
        } else {
            myIntent = new Intent(MainActivity.this, AlarmNoficationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0,myIntent,0);
        }

        if(!isRepeat){
            manager.set(AlarmManager.RTC_WAKEUP,SystemClock.elapsedRealtime()+3000,pendingIntent);
        } else {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,3000,pendingIntent);
        }


    }
}

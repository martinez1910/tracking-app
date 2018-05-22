package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.abamobile.empleodigital.trackingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_clockIn:
                Intent intent = new Intent(this, ClockInActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_forget:
                Snackbar.make(view, R.string.Snackbar_message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
    }
}

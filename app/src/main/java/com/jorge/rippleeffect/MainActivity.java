package com.jorge.rippleeffect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.widget.Toast;

import com.jorge.library.RippleLayout;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.stopMethodTracing();
        RippleLayout findViewById = (RippleLayout) findViewById(R.id.ripplelayout);
        findViewById.setOnRippleCompleteListener(new RippleLayout.OnRippleCompleteListener() {

            @Override
            public void onComplete(int id) {
                if(id==R.id.button01){
                    startActivity(new Intent(MainActivity.this, NewActivity.class));
                }else if(id==R.id.button02){
                    Toast.makeText(getApplicationContext(), "Button02", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.button04){
                    Toast.makeText(getApplicationContext(), "ImageButton", Toast.LENGTH_SHORT).show();
                }

            }


        });



    }
}
package com.example.mysudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class WinActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    public void playAgain (View v){
        String value = getIntent().getStringExtra("chronometre");
        Log.d("chronometer","La valeur de chrono est de " + value);

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}

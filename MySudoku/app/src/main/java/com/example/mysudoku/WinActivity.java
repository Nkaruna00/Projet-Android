package com.example.mysudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Intent i = getIntent();
        TextView text =(TextView) findViewById(R.id.chrono);
        text.setText("in " + i.getStringExtra("chrono"));
    }

    public void playAgain (View v){
        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void exitActivity (View v){
        finish();
    }

}

package com.example.mysudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.simpleChronometer);
        chronometer.start();
        //Intent i = new Intent(this,WinActivity.class);
        //startActivity(i);
    }


    boolean compareTabs(GameBoard gameboard){
        for(int i =0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                if(gameboard.cells[i][j].getRealValue() != gameboard.cells[i][j].getAssumedValue()){
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkWin(GameBoard gameboard){
        if(compareTabs(gameboard) == true){
            System.out.println("Win !");
            Intent i = new Intent(this,WinActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("chrono",chronometer.getText().toString());
            startActivity(i);
            finish();

            return true;
        }
        else{
            return false;
        }
    }

    public void exitActivity (View v){
        finish();
    }
}

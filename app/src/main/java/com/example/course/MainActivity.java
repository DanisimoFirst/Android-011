package com.example.course;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_calc).setOnClickListener(this::buttonCalcClick);
        findViewById(R.id.button_game).setOnClickListener(this::buttonGameClick);
        findViewById(R.id.button_chat).setOnClickListener(this::buttonChatClick);
    }

//    private void ButtonClick(View view){
////        TextView textView = findViewById(R.id.textView2);
////        String txt = textView.getText().toString();
////        txt += "!";
////        textView.setText(txt);
//    }


    private void buttonCalcClick(View view){
        Intent activityIntent = new Intent(MainActivity.this, CalcActivity.class);
        startActivity(activityIntent);
    }

    private void buttonGameClick(View view){
        Intent activityIntent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(activityIntent);
    }

    private void buttonChatClick(View view){
        Intent activityIntent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(activityIntent);
    }
}
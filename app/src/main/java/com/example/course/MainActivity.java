package com.example.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::ButtonClick);
    }

    private void ButtonClick(View view){
        TextView textView = findViewById(R.id.textView2);
        String txt = textView.getText().toString();
        txt += "!";
        textView.setText(txt);
    }
}
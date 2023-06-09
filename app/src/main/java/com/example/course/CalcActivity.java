package com.example.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class CalcActivity extends AppCompatActivity {


    private TextView tvHistory;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

      tvHistory = findViewById(R.id.tv_history) ;
      tvResult = findViewById(R.id.tv_result) ;
      clearClick(null);



      findViewById(R.id.calc_btn_0).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_1).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_2).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_3).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_4).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_5).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_6).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_7).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_8).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_9).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_plus).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_minus).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_back).setOnClickListener(this::backspaseClick);
      findViewById(R.id.calc_btn_clear).setOnClickListener(this::clearClick);
      findViewById(R.id.calc_btn_ce).setOnClickListener(this::clearEdit);
      findViewById(R.id.calc_btn_equals).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_koma).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_plusMinus).setOnClickListener(this::plusMinusClick);
      findViewById(R.id.calc_btn_percent).setOnClickListener(this::digitClick);
      findViewById(R.id.calc_btn_multiply).setOnClickListener(this::digitClick);
      findViewById(R.id.xSquared).setOnClickListener(this::squareClick);
      findViewById(R.id.calc_btn_equals).setOnClickListener(this::equalsClick);


    }

    @Override
    protected void onSaveInstanceState (@NonNull Bundle savingState){
        super.onSaveInstanceState(savingState);
        Log.d("CalcActivity", "onSaveInstanceSave");
        savingState.putCharSequence("history", tvHistory.getText());
        savingState.putCharSequence("result", tvResult.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        Log.d("CalcActivity", "onSaveInstanceSave");

        tvHistory.setText(savedState.getCharSequence("history"));
        tvResult.setText(savedState.getCharSequence("result"));
    }

    private void digitClick(View view){
     String result = tvResult.getText().toString();
     String digit = ((Button) view).getText().toString();

        if (result.equals("0")){
            result = "";

        }
        result+=digit;

        if (result.length() >= 10){
         return;
        }
        tvResult.setText(result);

    }

    private void displayResult(String result){
        if("".equals(result) ){
            result = getString(R.string.calc_btn_0_text);

        }
        tvResult.setText(result);
    }

    private void backspaseClick(View view){
        String result = tvResult.getText().toString() ;
        result = result.substring(0, result.length() - 1);
        displayResult( result ) ;
    }

    private void plusMinusClick(View view){

        //TODO
    }

    private void clearClick (View view){
        tvHistory.setText("");
        displayResult("");
        Vibrator vibrator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            VibratorManager vibratorManager = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
            vibrator = vibratorManager.getDefaultVibrator();
        }
        vibrator =  (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            vibrator.vibrate( VibrationEffect.createOneShot(
                     250, VibrationEffect.DEFAULT_AMPLITUDE
                    )
            );
            long[] vibratePattern = {10, 200, 100, 200};
             vibrator.vibrate(
                VibrationEffect.createWaveform(vibratePattern,-1)
        );
        } else {
            vibrator.vibrate(250);

        }


    }

    private void clearEdit (View view){
        displayResult("");
    }

    private void squareClick(View view){
        String result = tvResult.getText().toString();
        tvHistory.setText(getString(R.string.calc_btn_backspace_xSquared_history, result));
        double arg = Double.parseDouble(result);
        arg *= arg;
        result = arg + "";
        long argInt = (long) arg;
         result = argInt == arg ? "" + argInt : "" + arg ;

        displayResult(result);


    }

    private void equalsClick(View view) {
        String expression =  tvResult.getText().toString();
        if (expression.isEmpty()) {
            return;
        }
        String[] tokens = expression.split(" ");
        if (tokens.length < 3) {
            return;
        }
        double result = Double.parseDouble(tokens[0]);
        char operator = ' ';
        for (int i = 1; i < tokens.length; i++) {
            if (i % 2 == 0) {
                double operand = Double.parseDouble(tokens[i]);
                switch (operator) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '÷':
                        result /= operand;
                        break;
                }
            } else {
                operator = tokens[i].charAt(0);
            }
        }
        tvHistory.setText("");
        tvResult.setText(String.valueOf(result));
    }

}

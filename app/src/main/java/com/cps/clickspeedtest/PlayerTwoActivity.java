package com.cps.clickspeedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;

import com.cps.clickspeedtest.databinding.ActivityPlayerTwoBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PlayerTwoActivity extends AppCompatActivity {
    ActivityPlayerTwoBinding binding;
    private int mCounter1 = 0;
    private int mCounter2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter1++;
                binding.score1.setText(String.valueOf(mCounter1));
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter2++;
                binding.score2.setText(String.valueOf(mCounter2));
            }
        });

        new CountDownTimer(11000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");

                long sec = (millisUntilFinished / 1000) % 60;

                binding.txtTime.setText(f.format(sec));
            }

            @Override
            public void onFinish() {
                binding.txtTime.setText("00");
                binding.btn1.setEnabled(false);
                binding.btn2.setEnabled(false);
                move();
            }
        }.start();

    }
    private void move(){
        if (!binding.btn1.isEnabled()){
            Thread thread = new Thread(){
                public void run(){
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        Intent intent = new Intent(PlayerTwoActivity.this, ScoreTwoActivity.class);
                        intent.putExtra("score1", binding.score1.getText().toString());
                        intent.putExtra("score2", binding.score2.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                }
            };
            thread.start();
        }
    }

    @Override
    public void onBackPressed() { }
}
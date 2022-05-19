package com.cps.clickspeedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;

import com.cps.clickspeedtest.databinding.ActivityPlayerOneBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PlayerOneActivity extends AppCompatActivity {
    private ActivityPlayerOneBinding binding;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnClickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter++;
                binding.txtScore.setText(Integer.toString(mCounter));
            }
        });

        new CountDownTimer(11000, 1000) {
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
                binding.btnClickme.setEnabled(false);
                move();
            }
        }.start();

    }

    private void move() {
        if (!binding.btnClickme.isEnabled()) {
            String finalScore = binding.txtScore.getText().toString();
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(PlayerOneActivity.this, ScoreActivity.class);
                        intent.putExtra("score", finalScore);
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
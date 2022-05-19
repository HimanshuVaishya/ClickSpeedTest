package com.cps.clickspeedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cps.clickspeedtest.databinding.ActivityScoreTwoBinding;

public class ScoreTwoActivity extends AppCompatActivity {

    private ActivityScoreTwoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        String score1 = intent.getStringExtra("score1");
        String score2 = intent.getStringExtra("score2");

        binding.score1.setText(score1);
        binding.score2.setText(score2);

        binding.btnAgain.setOnClickListener(view -> intent(PlayerTwoActivity.class));
        binding.btnMenu.setOnClickListener(view -> intent(MainActivity.class));
    }
    private void intent(Class activity){
        Intent intent = new Intent(ScoreTwoActivity.this, activity);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() { }
}
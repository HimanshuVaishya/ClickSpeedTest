package com.cps.clickspeedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cps.clickspeedtest.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {
    private ActivityScoreBinding binding;
    String NewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        NewScore = intent.getStringExtra("score");
        binding.txtNewScore.setText(NewScore);

        loadscore();

        if ((Integer.valueOf(binding.txtNewScore.getText().toString()) >= (Integer.valueOf(binding.txtHighScore.getText().toString())))) {
            savescore();
        }

        binding.btnAgain.setOnClickListener(view -> intent(PlayerOneActivity.class));
        binding.btnMenu.setOnClickListener(view -> intent(MainActivity.class));
    }

    private void intent(Class activity) {
        Intent intent1 = new Intent(ScoreActivity.this, activity);
        startActivity(intent1);
        finish();
    }

    private void savescore() {
        SharedPreferences preferences = getSharedPreferences("sharepref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("text", binding.txtNewScore.getText().toString());
        editor.apply();
    }

    private void loadscore() {
        String text;
        SharedPreferences preferences = getSharedPreferences("sharepref", MODE_PRIVATE);
        text = preferences.getString("text", "00");
        binding.txtHighScore.setText(text);
    }

    @Override
    public void onBackPressed() { }
}
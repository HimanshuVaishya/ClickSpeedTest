package com.cps.clickspeedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cps.clickspeedtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnplayer1.setOnClickListener(view -> intent(PlayerOneActivity.class));
        binding.btnplayer2.setOnClickListener(view -> intent(PlayerTwoActivity.class));
        binding.btnabout.setOnClickListener(view -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

    }

    private void intent(Class activity) {
        Intent intent1 = new Intent(MainActivity.this, activity);
        startActivity(intent1);
        finish();
    }
}
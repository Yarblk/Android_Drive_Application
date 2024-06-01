// java/com/example/autotest/MainActivity.java
package com.example.autotest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView noviceImage = findViewById(R.id.novice_image);
        ImageView intermediateImage = findViewById(R.id.intermediate_image);
        ImageView expertImage = findViewById(R.id.expert_image);

        noviceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EasyLevelActivity.class);
                startActivity(intent);
            }
        });

        intermediateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MediumLevelActivity.class);
                startActivity(intent);
            }
        });

        expertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HardLevelActivity.class);
                startActivity(intent);
            }
        });
    }

}

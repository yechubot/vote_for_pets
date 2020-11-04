package com.example.vote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_result, btn_rank;
    Intent intent;
    int imgId[] = {R.id.cat, R.id.dog, R.id.rabbit};
    String imgName[] = {"cat", "dog", "rabbit"};
    ImageView[] img = new ImageView[3];
    int counts[] = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("vote for your pet!");
        btn_rank = findViewById(R.id.btn_rank);
        btn_result = findViewById(R.id.btn_result);
        for (int i = 0; i < imgId.length; i++) {
            img[i] = findViewById(imgId[i]);
        }

        //click img , show toast msg
        for (int i = 0; i < imgId.length; i++) {
            final int index = i;
            img[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counts[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index] + " got " + counts[index] + " votes", Toast.LENGTH_SHORT).show();
                }
            });
        }
        btn_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RankActivity.class);
                intent.putExtra("VoteCounts",counts);
                intent.putExtra("ImgName",imgName);
                startActivity(intent);
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCounts",counts);
                intent.putExtra("ImgName",imgName);
                startActivity(intent);
            }
        });
    }
}
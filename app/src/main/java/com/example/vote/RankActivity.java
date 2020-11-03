package com.example.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class RankActivity extends AppCompatActivity {
    Button btn_flip, btn_pause;
    ViewFlipper flipper;
    TextView[] rank = new TextView[3];
    int rankIds[] = {R.id.vf_rank1, R.id.vf_rank2, R.id.vf_rank3};
    ImageView[] imgs = new ImageView[3];
    int imgIds[] = {R.id.vf_image1,R.id.vf_image2, R.id.vf_image3};
    TextView[] names = new TextView[3];
    int nameIds[] = {R.id.vf_name1, R.id.vf_name2, R.id.vf_name3};
    Intent gintent;
    int[] voteC;
    String[] imgName;
    int[] img;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        flipper.setFlipInterval(2000);
        btn_flip = findViewById(R.id.btn_flip);
        btn_pause = findViewById(R.id.btn_pause);
        for (int i = 0; i < rank.length; i++) {
            rank[i] = findViewById(rankIds[i]);
            imgs[i] = findViewById(imgIds[i]);
            names[i] = findViewById(nameIds[i]);
        }
        gintent = getIntent();
        voteC = gintent.getIntArrayExtra("VoteCounts");
        imgName = gintent.getStringArrayExtra("ImgName");
        img = gintent.getIntArrayExtra("Img");
        //sort
        for (int a = 0; a < rankIds.length - 1; a++) {
            for (int b = a + 1; b < rankIds.length; b++) {
                if (voteC[a] <= voteC[b]) {
                    temp = voteC[b];
                    voteC[b] = voteC[a];
                    voteC[a] = temp;
                }
            }
        }
        //set
        for(int i=0; i<rank.length; i++){
            rank[i].setText("rank"+(i+1));
            imgs[i].setImageResource(img[i]);
            names[i].setText(imgName[i]);
        }
        btn_flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.startFlipping();
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.stopFlipping();
            }
        });
    }
}
package com.example.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class RankActivity extends AppCompatActivity {
    Button btn_flip, btn_pause;
    ViewFlipper flipper;
    int rankIds[] = {R.id.vf_rank1, R.id.vf_rank2, R.id.vf_rank3};
    int imgIds[] = {R.id.vf_image1, R.id.vf_image2, R.id.vf_image3};
    int nameIds[] = {R.id.vf_name1, R.id.vf_name2, R.id.vf_name3};
    int[] srcIds = {R.drawable.cat, R.drawable.dog, R.drawable.rabbit};
    int[] voteC;//투표 수 받음
    String[] imgName;//펫 이름 받음
    int temp, tempImg;
    String tempName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        btn_flip = findViewById(R.id.btn_flip);
        btn_pause = findViewById(R.id.btn_pause);
        flipper = findViewById(R.id.flipper);
        flipper.setFlipInterval(2000);
        Intent gintent = getIntent();
        voteC = gintent.getIntArrayExtra("VoteCounts");
        imgName = gintent.getStringArrayExtra("ImgName");
        //sort
        for (int i = 0; i < imgIds.length - 1; i++) {
            for (int j = i + 1; j < imgIds.length; j++) {
                if (voteC[i] < voteC[j]) {
                    //votes
                    temp = voteC[i];
                    voteC[i] = voteC[j];
                    voteC[j] = temp;
                    //name
                    tempName = imgName[i];
                    imgName[i] = imgName[j];
                    imgName[j] = tempName;
                    //img
                    tempImg = imgIds[i];
                    imgIds[i] = imgIds[j];
                    imgIds[j] = tempImg;
                }
            }
        }
        //setting
        for(int i=0; i<imgIds.length; i++){
            TextView tv = findViewById(rankIds[i]);
            tv.setText((i+1)+"등");
            ImageView iv = findViewById(imgIds[i]);
            iv.setImageResource(srcIds[i]);
            TextView tvn = findViewById(nameIds[i]);
            tvn.setText(imgName[i]+" got "+voteC[i] +" votes");

        }
        btn_flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.startFlipping();
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.stopFlipping();
            }
        });

    }
}
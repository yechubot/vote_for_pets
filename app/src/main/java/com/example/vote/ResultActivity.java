package com.example.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView bestName;
    ImageView bestImage;
    Button btn_toMain;
    TextView[] ts = new TextView[3];
    int[] tId = {R.id.t1, R.id.t2, R.id.t3};
    RatingBar[] ratingBar = new RatingBar[3];
    int[] barId = {R.id.bar1, R.id.bar2, R.id.bar3};
    int[] imgIds = {R.drawable.cat,R.drawable.dog,R.drawable.rabbit};
    int[] voteC;
    String[] imgName;
    int[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btn_toMain = findViewById(R.id.btn_toMain);
        bestName = findViewById(R.id.bestName);
        bestImage = findViewById(R.id.bestImage);
        for (int i = 0; i < barId.length; i++) {
            ts[i] = findViewById(tId[i]);
            ratingBar[i] = findViewById(barId[i]);
        }
        Intent gintent = getIntent();
        voteC = gintent.getIntArrayExtra("VoteCounts");
        imgName = gintent.getStringArrayExtra("ImgName");
        int max =0;
        for(int i=0; i<tId.length; i++){
            ts[i].setText(imgName[i]+" got "+voteC[i]+" votes");
            ratingBar[i].setRating(voteC[i]);
            if(voteC[max]<voteC[i]){
                max = i;
            }
        }
        bestName.setText(imgName[max]);
        bestImage.setImageResource(imgIds[max]);
        btn_toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
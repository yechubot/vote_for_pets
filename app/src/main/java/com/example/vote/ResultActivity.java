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
    int[] barId = {R.id.bar_dog, R.id.cat, R.id.rabbit};
    Intent gintent;
    int[] voteC;
    String[] imgName;
    int[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btn_toMain = findViewById(R.id.btn_toMain);
        bestName = findViewById(R.id.bestName);
        for (int i = 0; i < barId.length; i++) {
            ts[i] = findViewById(tId[i]);
            ratingBar[i] = findViewById(barId[i]);
        }
        bestImage = findViewById(R.id.bestImage);
        gintent = getIntent();
        voteC = gintent.getIntArrayExtra("VoteCounts");
        imgName = gintent.getStringArrayExtra("ImgName");
        img = gintent.getIntArrayExtra("Img");
        int max = voteC[0];
        int index = 0;
        for (int i = 0; i < barId.length; i++) {
            ratingBar[i].setRating(voteC[i]);
            ts[i].setText(imgName[i] + "(votes: " + voteC[i] + ")");
            if (max <= voteC[i]) {
                max = voteC[i];
                index = i;
            }
        }
        bestName.setText(imgName[index]);
        bestImage.setImageResource(img[index]);
        btn_toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
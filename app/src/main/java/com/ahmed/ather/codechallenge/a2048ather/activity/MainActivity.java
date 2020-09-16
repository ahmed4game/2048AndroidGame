package com.ahmed.ather.codechallenge.a2048ather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.ahmed.ather.codechallenge.a2048ather.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.game_view)
    GridView gameView;
    @BindView(R.id.current_score)
    TextView currentScore;
    @BindView(R.id.best_score) TextView bestScore;
    @BindView(R.id.parent)
    ConstraintLayout parentLayout;
    @BindView(R.id.game_card_view)
    CardView gameCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        bestScore.setText(getScore(KEY,"0"));
    }
}
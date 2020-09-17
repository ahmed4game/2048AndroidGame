package com.ahmed.ather.codechallenge.a2048ather.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmed.ather.codechallenge.a2048ather.BoardHelper;
import com.ahmed.ather.codechallenge.a2048ather.OnSwipeTouchListener;
import com.ahmed.ather.codechallenge.a2048ather.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Arrays;
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

    BoardHelper boardHelper;
    public int gameScore;
    private HashMap<String, Integer> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialSetup();
    }

    private void initialSetup() {
        ButterKnife.bind(this);
        map = new HashMap<>();
        boardHelper = new BoardHelper(new BoardHelper.ScoreFeed() {
            @Override
            public void updateScore(int score) {
                showInfo("Points from Feed: "+score);
                gameScore = gameScore + score;
                MainActivity.this.updateScore(gameScore);
            }
        });
        boardHelper.populateBoardMap(map);
        String[] mapKey = boardHelper.generateIndex(true);
        map.put(mapKey[0],2);
        map.put(mapKey[1],2);
        setListenerToLayouts(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipeBottom() {
                boardHelper.moveBoardsVertically(map,false);
                onGridChanged(boardHelper.getMapKeyList());
                showInfo("onSwipeBottom");
            }

            @Override
            public void onSwipeLeft() {
                boardHelper.moveBoardsHorizontally(map,true);
                onGridChanged(boardHelper.getMapKeyList());
                showInfo("onSwipeLeft");
            }

            @Override
            public void onSwipeRight() {
                boardHelper.moveBoardsHorizontally(map,false);
                onGridChanged(boardHelper.getMapKeyList());
                showInfo("onSwipeRight");
            }

            @Override
            public void onSwipeTop() {
                boardHelper.moveBoardsVertically(map,true);
                onGridChanged(boardHelper.getMapKeyList());
                showInfo("onSwipeTop");
            }
        });
        bestScore.setText(getScore(KEY,"0"));
        onGridChanged(boardHelper.getMapKeyList());
    }

    private void updateScore(int score) {
        currentScore.setText(""+score);
        if (score > Integer.parseInt(bestScore.getText().toString())){
            bestScore.setText(""+score);
            storeScore(KEY,score+"");
        }
    }

    void setListenerToLayouts(View.OnTouchListener listener){
        parentLayout.setOnTouchListener(listener);
        gameView.setOnTouchListener(listener);
    }

    public void onGridChanged(final ArrayList<String> list){
        gameView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position,convertView,parent);

                //  Converting view to TextView
                final TextView textView = (TextView)view;
                RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
                );
                textView.setLayoutParams(params);
                textView.setWidth(150);
                textView.setHeight(250);
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(34f);
                textView.setTypeface(null, Typeface.BOLD);


                //  set the value to TextView
                if(map.get(list.get(position))!=0) {
//                    showInfo(position+"");
//                    showInfo(list.get(position));
                    final int value = map.get(list.get(position));
                    YoYo.with(Techniques.Pulse)
                            .duration(250)
                            .onStart(new YoYo.AnimatorCallback() {
                                @Override
                                public void call(Animator animator) {
                                    textView.setText(value+"");
                                }
                            })
                            .playOn(textView);
//                    showInfo("Value = "+value+" Position:"+position);

                    changeCellColorBasedOnValue(textView, value);
                }
                else {
                    textView.setText("");
                }
                textView.setId(position);

                return textView;
            }
        });
    }

    private void changeCellColorBasedOnValue(TextView textView, int value) {
        switch (value){
            case 2:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_2));
                break;
            case 4:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_4));
                break;
            case 8:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_8));
                break;
            case 16:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_16));
                break;
            case 32:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_32));
                break;
            case 64:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_64));
                break;
            case 128:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_128));
                textView.setTextSize(24f);
                break;
            case 256:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_256));
                textView.setTextSize(24f);
                break;
            case 512:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_512));
                textView.setTextSize(19f);
                break;
            case 1024:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_1024));
                textView.setTextSize(19f);
                break;
            case 2048:
                textView.setBackgroundColor(getResources().getColor(R.color.cellColor_2048));
                textView.setTextSize(19f);
                break;
        }
    }
}
package com.ahmed.ather.codechallenge.a2048ather.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmed.ather.codechallenge.a2048ather.helper.BoardHelper;
import com.ahmed.ather.codechallenge.a2048ather.implementation.OnSwipeTouchListener;
import com.ahmed.ather.codechallenge.a2048ather.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
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
    @BindView(R.id.game_over_layout) ConstraintLayout gameOverLayout;
    @BindView(R.id.content_title) TextView contentTitle;
    @BindView(R.id.reset_button) Button resetButton;

    private BoardHelper boardHelper;
    public int gameScore;
    private final int scoreToWin = 2048, scoreToSpawn4 = 16;
    private HashMap<String, Integer> map;
    private OnSwipeTouchListener swipeTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialSetup();
    }

    private void initialSetup() {
        ButterKnife.bind(this);
        map = new HashMap<>();
        swipeTouchListener = new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipeBottom() {
                boardHelper.moveBoardsVertically(map,false);
                onGridChanged(boardHelper.getMapKeyList());
                checkPossibleMove();
                showInfo("onSwipeBottom");
            }

            @Override
            public void onSwipeLeft() {
                boardHelper.moveBoardsHorizontally(map,true);
                onGridChanged(boardHelper.getMapKeyList());
                checkPossibleMove();
                showInfo("onSwipeLeft");
            }

            @Override
            public void onSwipeRight() {
                boardHelper.moveBoardsHorizontally(map,false);
                onGridChanged(boardHelper.getMapKeyList());
                checkPossibleMove();
                showInfo("onSwipeRight");
            }

            @Override
            public void onSwipeTop() {
                boardHelper.moveBoardsVertically(map,true);
                onGridChanged(boardHelper.getMapKeyList());
                checkPossibleMove();
                showInfo("onSwipeTop");
            }
        };
        boardHelper = new BoardHelper(new BoardHelper.ScoreFeed() {
            @Override
            public void updateScore(int score) {
                showInfo("Points from Feed: "+score);
                gameScore = gameScore + score;
                MainActivity.this.updateScore(gameScore);
                if (score == scoreToWin)
                    gameWon();
                else if (score == scoreToSpawn4)
                    boardHelper.setTimeToSpawn4(true);
            }
        });
        boardHelper.populateBoardMap(map);
        String[] mapKey = boardHelper.generateIndex(true);
        map.put(mapKey[0],2);
        map.put(mapKey[1],2);
        setListenerToLayouts(swipeTouchListener);
        bestScore.setText(getScore(KEY,"0"));
        onGridChanged(boardHelper.getMapKeyList());
    }

    public void resetGame(){
        boardHelper.populateBoardMap(map);
        String[] mapKey = boardHelper.generateIndex(true);
        map.put(mapKey[0],2);
        map.put(mapKey[1],2);
        onGridChanged(boardHelper.getMapKeyList());
        gameOverLayout.setVisibility(View.GONE);
        gameOverLayout.setBackgroundColor(getColor(R.color.game_over_background));
        contentTitle.setText("Game Over!");
        contentTitle.setTextColor(getColor(R.color.cardview_dark_background));
        resetButton.setVisibility(View.GONE);
        setListenerToLayouts(swipeTouchListener);
        boardHelper.setTimeToSpawn4(false);
    }

    private void updateScore(int score) {
        currentScore.setText(""+score);
        if (score > Integer.parseInt(bestScore.getText().toString())){
            bestScore.setText(""+score);
            storeScore(KEY,score+"");
        }
    }

    private void checkPossibleMove(){
        int noOfEmptyTiles = boardHelper.getNoOfEmptyTiles(boardHelper.getArrayOfRows(),map).size();
        if (boardHelper.possibleMoveAvailable(map)){
            showInfo("There is Possibility");
        }else if (noOfEmptyTiles == 0) {
                showInfo("There is no Possibility");
                gameOverLayout.setVisibility(View.VISIBLE);
        }else{
            showInfo("There is Possibility because noOfEmptyTile is "+noOfEmptyTiles);
        }
    }

    private void gameWon(){
        gameOverLayout.setBackgroundColor(getColor(R.color.game_won_background));
        contentTitle.setTextColor(getColor(R.color.cardview_light_background));
        contentTitle.setText("You win!");
        gameOverLayout.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.VISIBLE);
        setListenerToLayouts(null);
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
                textView.setTextColor(getColor(R.color.cardview_light_background));
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

    public void onReset(View view) {
        resetGame();
    }
}
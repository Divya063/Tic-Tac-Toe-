package com.example.divyarani.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 for orange and 1 for blue
    boolean gameisactive=true;
    int activeplayer=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameisactive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.golmini);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.bluemini);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for (int[] winningposition : winningpositions) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]]
                        && gamestate[winningposition[0]] != 2) {
                    gameisactive = false;
                    String message = "Orange Minion";
                    if ((gamestate[winningposition[0]]) == 1) {
                        message = "Blue Minion";
                    }

                    //someone has won
                    TextView winnermessage = (TextView) findViewById(R.id.textView2);
                    winnermessage.setText(message + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playlayout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameisover = true;
                    for (int counterState : gamestate) {
                        if (counterState == 2) gameisover = false;
                    }
                    if (gameisover) {

                        TextView winnermessage = (TextView) findViewById(R.id.textView2);
                        winnermessage.setText("It is a draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playlayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playagain(View view){
        gameisactive=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playlayout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer=0;
        for (int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridlayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridlayout.getChildCount();i++)
        {
            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

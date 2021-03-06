package com.example.whichnumberisbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //instance variables for for the widgets we need to
    //access programmatically
    private Button buttonLeft;
    private Button buttonRight;
    private TextView textViewScore;

    //instance variables for numbers and score
    private int score;
    private int leftNum;
    private int rightNum;

    public static final int MAX_NUM = 1000;
    public static final int Min_NUM = 0;

    public boolean isLeftBigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        randomizeAndUpdateDisplay();

    }

    private void randomizeAndUpdateDisplay() {

        //access the string resources using getResources()
        String scoreString = getResources().getString(R.string.main_score);
        textViewScore.setText(scoreString + score);
        randomizeNumbers();
        buttonLeft.setText(String.valueOf(leftNum));
        buttonRight.setText(String.valueOf(rightNum));

    }

    private void randomizeNumbers() {

        //generate a random number for the left
        leftNum = genNumber();
        rightNum = genNumber();
        //generate a radom number for the right by make sure it does
        //match the left
        while(leftNum == rightNum){

            rightNum = genNumber();

        }

        if (leftNum > rightNum) {

            isLeftBigger = true;

        }

    }

    private int genNumber () {

        int range = MAX_NUM - Min_NUM + 1;
        return Min_NUM + (int)(Math.random()* range);

    }

    private void wireWidgets() {

        buttonLeft = findViewById(R.id.button_main_leftNum);
        buttonRight = findViewById(R.id.button_main_rightNum);
        textViewScore = findViewById(R.id.textView_main_score);

    }

    public void onRightClick(View view) {

        String message;
        if (!isLeftBigger == false) {
            score++;
            message = "Correct";
        }
        else {
            score--;
            message = "Wrong";
        }
        randomizeAndUpdateDisplay();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onLeftClick(View view) {

        String message;
        if (isLeftBigger == true) {
            score++;
            message = "Correct";

        }
        else {
            score--;
            message = "Wrong";
        }
        randomizeAndUpdateDisplay();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

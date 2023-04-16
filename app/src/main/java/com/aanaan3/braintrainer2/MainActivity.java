package com.aanaan3.braintrainer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.aanaan3.braintrainer2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    int a = 0;
    int b = 0;

    ArrayList<Integer> answers = new ArrayList<>();

    int locationOfCorrectAnswer;
    CountDownTimer mCountDownTimer;

    int correctAnswer = 0;
    int numberOfQuestion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


    }

    public void startGame(View view){
        mBinding.constraintLayout2.setVisibility(View.VISIBLE);
        mBinding.btnGo.setVisibility(View.GONE);
        mBinding.txtViewStatus.setVisibility(View.INVISIBLE);
        mBinding.btnPlayAgain.setVisibility(View.INVISIBLE);

        fillImageView();
        timer();
    }

    public void fillImageView(){
        Random random = new Random();
        a = random.nextInt(21);
        b = random.nextInt(21);
        mBinding.txtViewQuestion.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        mBinding.txtView1.setText(String.valueOf(answers.get(0)));
        mBinding.txtView2.setText(String.valueOf(answers.get(1)));
        mBinding.txtView3.setText(String.valueOf(answers.get(2)));
        mBinding.txtView4.setText(String.valueOf(answers.get(3)));

    }

    public void clickItem(View view){

        int tapped = Integer.parseInt(view.getTag().toString());

        if (tapped == locationOfCorrectAnswer){
            mBinding.txtViewStatus.setVisibility(View.VISIBLE);
            mBinding.txtViewStatus.setText("Correct");
            correctAnswer = correctAnswer +1;
        }else {
            mBinding.txtViewStatus.setVisibility(View.VISIBLE);
            mBinding.txtViewStatus.setText("Wrong");
        }

        numberOfQuestion++;

        mBinding.txtViewScore.setText(correctAnswer +"/"+numberOfQuestion);

        answers.clear();
        fillImageView();
    }





    public void timer(){
        mCountDownTimer = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long l) {
                int s = (int) (l/1000);
                mBinding.txtViewSecond.setText(s + "s");

            }

            @Override
            public void onFinish() {
                mBinding.btnPlayAgain.setVisibility(View.VISIBLE);
                mBinding.txtViewStatus.setText("Done!");
                mBinding.txtView1.setClickable(false);
                mBinding.txtView2.setClickable(false);
                mBinding.txtView3.setClickable(false);
                mBinding.txtView4.setClickable(false);
            }
        }.start();
    }

    public void playAgain(View view){
        mBinding.tableLayout.setClickable(true);
        mBinding.txtViewScore.setText(R.string.score);
        mBinding.txtViewQuestion.setText(R.string.question);
        mBinding.txtViewStatus.setText("");
        mBinding.btnPlayAgain.setVisibility(View.INVISIBLE);
        mBinding.txtView1.setClickable(true);
        mBinding.txtView2.setClickable(true);
        mBinding.txtView3.setClickable(true);
        mBinding.txtView4.setClickable(true);
        answers.clear();
        fillImageView();
        correctAnswer =0;
        numberOfQuestion=0;
        timer();
    }
}
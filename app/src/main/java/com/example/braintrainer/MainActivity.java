package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button;
    ConstraintLayout gameLayout;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    int score = 0;
    int num_of_question =0;
    int loc_correct_ans;
    ArrayList<Integer> answers = new ArrayList<>();
    CountDownTimer count;

    // Function for choosing an answer and detecting if its wrong or correct
    @SuppressLint("SetTextI18n")
    public void ansClick(View view){
        if(Integer.toString(loc_correct_ans).equals(view.getTag().toString())){
            mediaPlayer3.start();
            textView4.setText("Correct! :)");
            score += 1;
        }
        else{
            mediaPlayer2.start();
            textView4.setText("Wrong! :(");
        }
        num_of_question +=1;
        textView3.setText(Integer.toString(score) + " / " + Integer.toString(num_of_question));
        newQuestion();
    }

     // Function to display the necessary buttons after the quiz ends

    public void show(View view){
        button2.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.textView));
    }

    // Function for framing and displaying the new question

    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        textView2.setText(Integer.toString(a) + " + " + Integer.toString(b));
        loc_correct_ans = random.nextInt(4);
        answers.clear();

        for(int i=0; i<4;i++){
            if(i == loc_correct_ans){
                answers.add((a+b));
            }
            else{
                int wrong_ans = random.nextInt(41);
                while(wrong_ans == a+b){
                    wrong_ans =  random.nextInt(41);
                }
                answers.add(wrong_ans);
            }
        }
        button3.setText(Integer.toString(answers.get(0)));
        button6.setText(Integer.toString(answers.get(1)));
        button5.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    // Function to play the quiz again

    public void playAgain(View view){
        button3.setClickable(true);
        button4.setClickable(true);
        button5.setClickable(true);
        button6.setClickable(true);
        score = 0;
        num_of_question = 0;
        textView4.setText(" ");
        textView.setText("30s");
        textView3.setText(Integer.toString(score) + " / " + Integer.toString(num_of_question));
        newQuestion();
        count = new CountDownTimer(30200,1000){
            @Override
            public void onTick(long l) {
                textView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                textView.setText("0s");
                mediaPlayer.start();
                textView4.setText("Time Up!");
                button.setVisibility(View.VISIBLE);
                button3.setClickable(false);
                button4.setClickable(false);
                button5.setClickable(false);
                button6.setClickable(false);
            }
        }.start();
        button.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button3 = findViewById(R.id.button3);
         button4 = findViewById(R.id.button4);
         button5 = findViewById(R.id.button5);
         button6 = findViewById(R.id.button6);
        button2 =(Button) findViewById(R.id.button2);
        textView =findViewById(R.id.textView);
        textView2 =findViewById(R.id.textView2);
        textView3 =findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
        mediaPlayer = MediaPlayer.create(this,R.raw.rancho);
        mediaPlayer2 = MediaPlayer.create(this,R.raw.beeps1);
        mediaPlayer3 = MediaPlayer.create(this,R.raw.zings1);
    }
}
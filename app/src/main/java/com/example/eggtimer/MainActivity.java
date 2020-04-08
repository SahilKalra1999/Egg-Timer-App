package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    Button controllerButton;
    Boolean counterIsActive=false;
    TextView timerTextView;
    SeekBar timerSeekBar;
    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("go!");
        timerSeekBar.setEnabled(true);
        counterIsActive=false;
    }
    public void updateTimer(int secondsLeft){
        int minutes= secondsLeft/60;
        int seconds=secondsLeft-minutes*60;
        String secondString;
        secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        timerTextView.setText(Integer.toString(minutes)+":"+ secondString );


    }
    public void controlTimer(View view){
        if(counterIsActive==false) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("stop");
            countDownTimer= new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long milliSeconds) {
                    updateTimer((int) milliSeconds / 1000);

                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();
        }
        else{
          resetTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controllerButton=(Button)findViewById(R.id.controllerButton);
         timerSeekBar=(SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

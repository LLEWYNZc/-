package com.example.neteastmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayingActivity extends AppCompatActivity {

    ImageView playBtn;
    SeekBar positionBar;
    TextView elapsedTimeLabel;
    TextView sumTimeLabel;
    MediaPlayer mp;
    int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_playing);

        //Get references to the positionBar, playBtn, elapsedTimeLabel, and SumTimeLabel
        playBtn = (ImageView) findViewById(R.id.playBtn);
        positionBar = findViewById(R.id.positionBar);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        sumTimeLabel = findViewById(R.id.sumTimeLabel);

        //Get the extra information from intent
        Intent intent = getIntent();
        int src = intent.getIntExtra("source", 0);


        //Create a media player
        mp = MediaPlayer.create(this, src);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        sumTimeLabel.setText(createTimeLabel(mp.getDuration()));
        totalTime = mp.getDuration();
        mp.start();
        playBtn.setImageResource(R.drawable.pause);


        //Position Bar
        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

        //Set OnClickListener on play button(Although it is a ImageView...)
        ImageView onPlayBtn = findViewById(R.id.playBtn);
        onPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBtnClick();
            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            positionBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick() {

        if (!mp.isPlaying()) {
            // Stopping
            mp.start();
            playBtn.setImageResource(R.drawable.pause);

        } else {
            // Playing
            mp.pause();
            playBtn.setImageResource(R.drawable.play);
        }

    }
}

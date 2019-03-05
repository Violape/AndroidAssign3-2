package com.example.game_animals;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Arrays.sort;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity {
    int [] init = new int[30];
    int [] arrf = new int[30];
    int match = -1;
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        for(int i=0; i<30; i++){
            init[i] = 1;
        }

        findViewById(R.id.imageView00).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[0] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[0]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView00))).setImageResource(sourceid);
                    init[0] = 1;
                    if(match == -1){
                        match = 0;
                    }
                    else {
                        final boolean pair = (arrf[0] == arrf[match]);
                        match = -1;
                        final Handler handler = new Handler();
                        Runnable runnable=new Runnable(){
                            @Override
                            public void run() {
                                if(pair){
                                    addscore();
                                }
                                else {
                                    String targetbox = "imageView" + String.valueOf(Integer.valueOf(match / 5)) + String.valueOf(match % 5);
                                    int targetid = getResources().getIdentifier(targetbox, "id", getPackageName());
                                    ((ImageView) (findViewById(targetid))).setImageResource(R.drawable.icon00);
                                    ((ImageView) (findViewById(R.id.imageView00))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[0] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void startGame(View view){
        for (int i=0; i<30; i++){
            init[i]= i;
        }
        int count = init.length;
        int cbRandCount = 0;
        int cbPosition = 0;
        int k = 0;
        do {
            Random rand = new Random();
            int r = count - cbRandCount;
            cbPosition = rand.nextInt(r);
            arrf[k++] = init[cbPosition];
            cbRandCount++;
            init[cbPosition] = init[r - 1];
        } while (cbRandCount < count);
        for(int i=0; i<30; i++){
            init[i] = 1;
        }
        for(int i=0; i<30; i++){
            String targetbox = "imageView" + String.valueOf(Integer.valueOf(i / 5)) + String.valueOf(i % 5);
            int targetid = getResources().getIdentifier(targetbox, "id", getPackageName());
            arrf[i] = (int)(arrf[i]/2)+1;
            String sourceimg = "icon" + String.valueOf(arrf[i]);
            int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
            ((ImageView)(findViewById(targetid))).setImageResource(sourceid);
        }
        final Handler handler = new Handler();
        Runnable runnable=new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    String targetbox = "imageView" + String.valueOf(Integer.valueOf(i / 5)) + String.valueOf(i % 5);
                    int targetid = getResources().getIdentifier(targetbox, "id", getPackageName());
                    ((ImageView) (findViewById(targetid))).setImageResource(R.drawable.icon00);
                }
                for(int i=0; i<30; i++){
                    init[i] = 0;
                }
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    public void addscore(){
        TextView now = findViewById(R.id.score);
        int score = Integer.valueOf(String.valueOf(now.getText()));
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(score + 1));
        if(score == 14){
            ;
        }
    }
}

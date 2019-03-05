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
        findViewById(R.id.imageView01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[1] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[1]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView01))).setImageResource(sourceid);
                    init[1] = 1;
                    if(match == -1){
                        match = 1;
                    }
                    else {
                        final boolean pair = (arrf[1] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView01))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[1] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[2] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[2]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView02))).setImageResource(sourceid);
                    init[2] = 1;
                    if(match == -1){
                        match = 2;
                    }
                    else {
                        final boolean pair = (arrf[2] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView02))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[2] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[3] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[3]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView03))).setImageResource(sourceid);
                    init[3] = 1;
                    if(match == -1){
                        match = 3;
                    }
                    else {
                        final boolean pair = (arrf[3] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView03))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[3] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[4] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[4]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView04))).setImageResource(sourceid);
                    init[4] = 1;
                    if(match == -1){
                        match = 4;
                    }
                    else {
                        final boolean pair = (arrf[4] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView04))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[4] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[5] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[5]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView10))).setImageResource(sourceid);
                    init[5] = 1;
                    if(match == -1){
                        match = 5;
                    }
                    else {
                        final boolean pair = (arrf[5] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView10))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[5] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[6] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[6]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView11))).setImageResource(sourceid);
                    init[6] = 1;
                    if(match == -1){
                        match = 6;
                    }
                    else {
                        final boolean pair = (arrf[6] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView11))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[6] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[7] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[7]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView12))).setImageResource(sourceid);
                    init[7] = 1;
                    if(match == -1){
                        match = 7;
                    }
                    else {
                        final boolean pair = (arrf[7] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView12))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[7] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[8] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[8]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView13))).setImageResource(sourceid);
                    init[8] = 1;
                    if(match == -1){
                        match = 8;
                    }
                    else {
                        final boolean pair = (arrf[8] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView13))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[8] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[9] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[9]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView14))).setImageResource(sourceid);
                    init[9] = 1;
                    if(match == -1){
                        match = 9;
                    }
                    else {
                        final boolean pair = (arrf[9] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView14))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[9] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[10] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[10]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView20))).setImageResource(sourceid);
                    init[10] = 1;
                    if(match == -1){
                        match = 10;
                    }
                    else {
                        final boolean pair = (arrf[10] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView20))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[10] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[11] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[11]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView21))).setImageResource(sourceid);
                    init[11] = 1;
                    if(match == -1){
                        match = 11;
                    }
                    else {
                        final boolean pair = (arrf[11] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView21))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[11] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[12] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[12]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView22))).setImageResource(sourceid);
                    init[12] = 1;
                    if(match == -1){
                        match = 12;
                    }
                    else {
                        final boolean pair = (arrf[12] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView22))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[12] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[13] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[13]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView23))).setImageResource(sourceid);
                    init[13] = 1;
                    if(match == -1){
                        match = 13;
                    }
                    else {
                        final boolean pair = (arrf[13] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView23))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[13] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[14] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[14]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView24))).setImageResource(sourceid);
                    init[14] = 1;
                    if(match == -1){
                        match = 14;
                    }
                    else {
                        final boolean pair = (arrf[14] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView24))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[14] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView30).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[15] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[15]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView30))).setImageResource(sourceid);
                    init[15] = 1;
                    if(match == -1){
                        match = 15;
                    }
                    else {
                        final boolean pair = (arrf[15] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView30))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[15] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[16] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[16]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView31))).setImageResource(sourceid);
                    init[16] = 1;
                    if(match == -1){
                        match = 16;
                    }
                    else {
                        final boolean pair = (arrf[16] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView31))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[16] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[17] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[17]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView32))).setImageResource(sourceid);
                    init[17] = 1;
                    if(match == -1){
                        match = 17;
                    }
                    else {
                        final boolean pair = (arrf[17] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView32))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[17] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[18] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[18]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView33))).setImageResource(sourceid);
                    init[18] = 1;
                    if(match == -1){
                        match = 18;
                    }
                    else {
                        final boolean pair = (arrf[18] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView33))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[18] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView34).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[19] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[19]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView34))).setImageResource(sourceid);
                    init[19] = 1;
                    if(match == -1){
                        match = 19;
                    }
                    else {
                        final boolean pair = (arrf[19] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView34))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[19] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView40).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[20] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[20]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView40))).setImageResource(sourceid);
                    init[20] = 1;
                    if(match == -1){
                        match = 20;
                    }
                    else {
                        final boolean pair = (arrf[20] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView40))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[20] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView41).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[21] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[21]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView41))).setImageResource(sourceid);
                    init[21] = 1;
                    if(match == -1){
                        match = 21;
                    }
                    else {
                        final boolean pair = (arrf[21] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView41))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[21] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView42).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[22] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[22]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView42))).setImageResource(sourceid);
                    init[22] = 1;
                    if(match == -1){
                        match = 22;
                    }
                    else {
                        final boolean pair = (arrf[22] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView42))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[22] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView43).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[23] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[23]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView43))).setImageResource(sourceid);
                    init[23] = 1;
                    if(match == -1){
                        match = 23;
                    }
                    else {
                        final boolean pair = (arrf[23] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView43))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[23] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView44).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[24] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[24]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView44))).setImageResource(sourceid);
                    init[24] = 1;
                    if(match == -1){
                        match = 24;
                    }
                    else {
                        final boolean pair = (arrf[24] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView44))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[24] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[25] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[25]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView50))).setImageResource(sourceid);
                    init[25] = 1;
                    if(match == -1){
                        match = 25;
                    }
                    else {
                        final boolean pair = (arrf[25] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView50))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[25] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView51).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[26] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[26]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView51))).setImageResource(sourceid);
                    init[26] = 1;
                    if(match == -1){
                        match = 26;
                    }
                    else {
                        final boolean pair = (arrf[26] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView51))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[26] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView52).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[27] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[27]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView52))).setImageResource(sourceid);
                    init[27] = 1;
                    if(match == -1){
                        match = 27;
                    }
                    else {
                        final boolean pair = (arrf[27] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView52))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[27] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView53).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[28] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[28]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView53))).setImageResource(sourceid);
                    init[28] = 1;
                    if(match == -1){
                        match = 28;
                    }
                    else {
                        final boolean pair = (arrf[28] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView53))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[28] = 0;
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        findViewById(R.id.imageView54).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init[29] == 1){
                    ;
                }
                else{
                    String sourceimg = "icon" + String.valueOf(Integer.valueOf(arrf[29]));
                    int sourceid = getResources().getIdentifier(sourceimg, "drawable", getPackageName());
                    ((ImageView)(findViewById(R.id.imageView54))).setImageResource(sourceid);
                    init[29] = 1;
                    if(match == -1){
                        match = 29;
                    }
                    else {
                        final boolean pair = (arrf[29] == arrf[match]);
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
                                    ((ImageView) (findViewById(R.id.imageView54))).setImageResource(R.drawable.icon00);
                                    init[match] = 0;
                                    init[29] = 0;
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
        ((TextView) findViewById(R.id.score)).setText("0");
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

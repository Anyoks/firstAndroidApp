package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by orinamokaya on 1/2/18.
 */

// this is the right way to do Graphix stuff because it is more cpu efficient
public class GFXSurface extends Activity implements View.OnTouchListener{

    MyBringBackSurface ourSurfaceView;
    float x,y;// will be used to get current touch coordinates
    float sX,sY; // starting X and Y points
    float fX, fY; // final x and Y points
    float dX, dY; // the changes in X and Y
    float aniX, aniY; // variable used to animate s
    float scaledX, scaledY; // this is used to reduce the final value of X and Y so that they don't go beyond the screen size
    Bitmap ball, plus, plus1; // global variable

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this); // similar to onclick
        x  = 0;
        y  = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniY = aniX = scaledX = scaledY = 0; // setting all variables to 0 in one line

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.brown_ball); // set up the ball we want to draw
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus); // set up the plus we want to draw
        plus1 = BitmapFactory.decodeResource(getResources(), R.drawable.pluspressed); // set up the plus we want to draw

        setContentView(ourSurfaceView);
    }

    // this method is defined in the MyBringBackSurface class
    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume(); // this pause method is the one we created
    }

    // this method is defined in the MyBringBackSurface class
    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause(); // this resume method is the one we created
    }

    //this method is called when our surface is touched.
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //this will allow the app to sleep and save processing power and cycles.
        // without this part of the code, this function will loop continuously
        //it'll save processing speed
        //a sleep time of 50ms will allow for 20 frames per second 1000/20 = 50
        try {
            Thread.sleep(50); //pause
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // get current coordinates of X and Y where the user touched continuously
        x = motionEvent.getX();
        y = motionEvent.getY();

        switch (motionEvent.getAction()){
            // here will get coordinates only when we press down and only when we release
            case MotionEvent.ACTION_DOWN: //when you first touch
                // get the starting x and y position for the plus bitmap
                sX = motionEvent.getX();
                sY = motionEvent.getY();
                // rest fY and fX to 0 tomake plus1 and the ball to disappear when screen is touched
                fX = fY = 0;
                // reset all animation values to 0 so they don't increase beyond the screen size
                dX = dY = aniY = aniX = scaledX = scaledY = 0;
                break;
            case MotionEvent.ACTION_UP: // when we release, or remove the finger from the screen
                fX = motionEvent.getX();
                fY = motionEvent.getY();
                // change in X & Y final X,Y - starting X,Y
                dX = fX -sX;
                dY = fY -sY;
                //scale down X and Y  to fit the screen
                scaledX = dX/30;
                scaledY = dY/30;
                //remove the left behind ball on release, make it disappear
                x = y = 0;
                break;
        }


        return true; // this makes the ball follow as the user drags the ball across the screen
        //when you return false, you don;t get any more coordinates from here, so no dragging.
    }


    /***************************************
     * *************************************
     * *************************************
     * here we'll define our OTHER CLASS ***
     * *************************************
     * *************************************
     * *************************************
     * */
// Surface view is more efficient in handling animations. it does it using a thread
// Surface view is basically the surface of the view, and not the whole view class.

    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder; // used to control or manage the surface
        Thread ourThread = null; // we must define a thread before using it.
        boolean isRunning = false;

        //constructor
        public MyBringBackSurface(Context context) {
            super(context);

            ourHolder = getHolder(); // the holder tells us if the surface is valid or available or not. also enalbles us to lock the canvas
        }

        // pause the thread

        public void pause() {
            isRunning = false; // break the loop
            while (true){
                try {
                    ourThread.join(); //letting the thread die
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null; // reset the thread to null
        }


        // resume the thread
        public void resume() {
            // the thread runs this code before it starts. This method is called each time the constructor is called
            isRunning = true; //continue the loop
            ourThread = new Thread(this);
            ourThread.start(); // start the thread
        }


        // the purpose of the thread is to animate the canvas
        // the code in here is what runs when the thread starts running
        // all animations are coded here
        @Override
        public void run() {
            // lets make the thread do something now...

            // this loop will run for as long as isRunnint is true and stop when it's false
            while (isRunning){
                // check if the surface is not valid the code will jump out of this loop, that's what continue does. otherwise it stays
                if (!ourHolder.getSurface().isValid())
                    continue; // break the loop

                //when the surface is valid, execute the loop
                Canvas canvas = ourHolder.lockCanvas(); // lock the canvas so that no other thread can access it till we unlock it
                canvas.drawRGB(02, 02, 150); // draw the background colour

                // this is where we draw the bBall
                if (x != 0 && y != 0){ //if the screen is touched ....
                    //make the ball appear at the center of where the user touches
                    float a,b;
                    a = x-(ball.getWidth()/2); // correct x position
                    b = y-(ball.getHeight()/2); //correct y position
                    canvas.drawBitmap(ball, a , b,null); // draw the ball on the canvas
                }

                // this is where we draw the starting Plus When the screen is touched. On Action Down
                if (sX != 0 && sY != 0){ //if the screen is touched ....
                    //make the plus appear at the center of where the user touches
                    float a1,b1;
                    a1 = sX-(plus.getWidth()/2); // correct x position
                    b1 = sY-(plus.getHeight()/2); //correct y position
                    canvas.drawBitmap(plus,a1,b1,null); // draw the plus on the canvas
                }
                // this is where we draw the final Plus when the finger removed. On Action UP
                if (fX != 0 && fY != 0){ //if the screen is touched ....
                    //make the red plus1   appear at the center of where the user touches
                    float a2,b2;
                    a2 = fX-(plus1.getWidth()/2); // correct x position
                    b2= fY-(plus1.getHeight()/2); //correct y position

                    canvas.drawBitmap(ball, fX-(ball.getWidth()/2)-aniX , fY-(ball.getHeight()/2)-aniY,null); //the aniX and aniY make the ball move
                    // draw the plus on the canvas
                    canvas.drawBitmap(plus1,a2,b2,null);
                }
                // moving the ball back to where it came from
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;

                ourHolder.unlockCanvasAndPost(canvas); // unlock the canvas and show the whole world.
            }
        }
    }

}

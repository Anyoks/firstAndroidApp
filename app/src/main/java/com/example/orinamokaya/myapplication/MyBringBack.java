package com.example.orinamokaya.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by orinamokaya on 1/2/18.
 */

public class MyBringBack extends View {

    Bitmap bBall; // we'll use this to hold the brown ball resource for GFX
    float changingY; // we'll use this for animation
    Typeface font; // we'll use this for a custom font

    // def class constructor
    public MyBringBack(Context context) {
        super(context);

        // get the bBall resource
        bBall = BitmapFactory.decodeResource(getResources(), R.drawable.brown_ball);
        changingY = 0;
        font  = Typeface.createFromAsset(context.getAssets(),"DIGIFACE.TTF"); // set up the custom font

    }

    //For us to draw the bBall, we need to use a canvas method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Lets make the background black
        canvas.drawColor(Color.WHITE);

        // put our font before the ball is drawn
        Paint textPaint = new Paint(); // a different way of setting the text color
        textPaint.setARGB(50,254,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER); // access the aling class to make it center
        textPaint.setTextSize(100);
        textPaint.setTypeface(font); // set our custom font here
        canvas.drawText("Faith on Fire",canvas.getWidth()/2, 400,textPaint); // draw the text

        // this is now we draw the bBall on the canvas, starting from the topLeft corner 0,0
        canvas.drawBitmap(bBall,(canvas.getWidth()/2),changingY,null);
        //lets vary changingY to make an animation
        if (changingY < canvas.getHeight()){
            changingY += 10;
        }else{
            changingY = 0;
        }

        //Just something interesting, lets draw a rectangle

        Rect middleRect = new Rect();
        middleRect.set(0,500 ,canvas.getWidth(),700); // similar to drawing the bBall
        //a new way to color
        Paint rectColor = new Paint();
        rectColor.setColor(Color.DKGRAY);
        canvas.drawRect(middleRect, rectColor); // draw the rectangle on the screen

        invalidate(); // this line of code will cause the above loop to repeat infinitely
    }
}

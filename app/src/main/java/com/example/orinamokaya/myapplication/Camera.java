package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by orinamokaya on 7/31/17.
 */

public class Camera extends Activity implements View.OnClickListener {

    /*
    * Initialize global variables
    * */
    ImageView iv;
    ImageButton ib;
    Button btn;
    Intent i;
    Bitmap bmp;
   final static int cameraData = 0;



    @Override
    /* THis onCreate method must be present in every app,i.e activity*/
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initialize();

    }

    private void initialize (){
        iv = findViewById(R.id.ivReturnedPic);
        ib = findViewById(R.id.ibTakePic);
        btn = findViewById(R.id.btnSetWall);

        btn.setOnClickListener(this);
        ib.setOnClickListener(this);

        InputStream inputStr = getResources().openRawResource(R.drawable.switch_it);
        bmp = BitmapFactory.decodeStream(inputStr);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSetWall:
                try {
                    getApplicationContext().setWallpaper(bmp);
                    //WallpaperManager.getInstance().setBitmap(bmp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ibTakePic:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*This Method checks of the result is there and if it is Ok.*/
        if (resultCode == RESULT_OK){
            Bundle extras = data.getExtras(); // Opposite of put extras :-)

            /* the (Bitmap) means, we are expecting data of type bitmap*/
            bmp = (Bitmap) extras.get("data"); // Getting some extras from an activity.
            iv.setImageBitmap(bmp); // setting the bitmap on our image view
        }
    }
}

package com.womenproiot.www.link;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class YourGifActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_gif);
        showGifImage();
//        try {
//            Thread.sleep(6500);
//        }catch (InterruptedException e){e.printStackTrace();}
//        startActivity(new Intent(this,MainActivity.class));
//        finish();
    }

    private void showGifImage() {
        ImageView intro=(ImageView)findViewById(R.id.gif_image);

        GlideDrawableImageViewTarget gifImage=new GlideDrawableImageViewTarget(intro);
        Glide.with(this).load(R.drawable.intro).into(gifImage);
    }


}
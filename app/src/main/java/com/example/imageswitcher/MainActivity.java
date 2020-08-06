package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    ImageSwitcher imageSwitcher;
    Button button;
    int image_data[] ={R.drawable.image1,R.drawable.images2,R.drawable.image3,R.drawable.images4,R.drawable.images5};
    String[] TEXTS = { "LION", "CAT", "PIGEON","PARROT","DOG" };
    int count=image_data.length;
    int image_index=-1;
    TextSwitcher mTextSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        imageSwitcher=findViewById(R.id.imageSwitcher);
        mTextSwitcher = findViewById(R.id.textSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());

                imageView.setImageResource(R.drawable.images2);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        Animation animation_in= AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        Animation animation_out= AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        imageSwitcher.setInAnimation(animation_in);
        imageSwitcher.setInAnimation(animation_out);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image_index++;
                if(image_index==count){
                    image_index=0;
                }
                imageSwitcher.setImageResource(image_data[image_index]);
                mTextSwitcher.setText(TEXTS[image_index]);





            }
        });

    }
}
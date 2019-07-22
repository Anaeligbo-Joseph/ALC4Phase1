package com.joseph.alc4.alc4phase1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imageView=findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.alc4pics)
                .transform(new CircleCrop())
                .into(imageView);


    }
}

package com.example.myapplication.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ImageView imageView = findViewById(R.id.imageView_animation_sysu);
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_out);
        animator.setTarget(imageView);
        animator.start();
        TextView textView = findViewById(R.id.text_view);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view:
                ObjectAnimator elevationAnimation =
                        ObjectAnimator.ofFloat(v, "elevation", 0);
                elevationAnimation.setRepeatCount(1);
                elevationAnimation.setDuration(800);
                elevationAnimation.setRepeatMode(ValueAnimator.REVERSE);
                elevationAnimation.start();
                break;
        }
    }
}

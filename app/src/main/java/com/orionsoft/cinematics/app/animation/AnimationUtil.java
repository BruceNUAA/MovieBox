package com.orionsoft.cinematics.app.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

public class AnimationUtil {
    public static  void animate(RecyclerView.ViewHolder holder , boolean goesDown){

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(holder.itemView,"translationY",goesDown==true?200:-200,0);
        animatorX.setDuration(1000);


        ObjectAnimator animatorY = ObjectAnimator.ofFloat(holder.itemView,"translationX",-50,50,-10,10,0);
        animatorY.setDuration(1000);

        animatorSet.playTogether(animatorY);
        animatorSet.start();

    }
}

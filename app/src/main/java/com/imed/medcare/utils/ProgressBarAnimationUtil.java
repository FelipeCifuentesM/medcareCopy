package com.imed.medcare.utils;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Ramiro on 06-06-2018.
 */

public class ProgressBarAnimationUtil extends Animation{
    private ProgressBar progressBar;
    private float from;
    private float  to;
    private TextView progress;

    public ProgressBarAnimationUtil(TextView progress, ProgressBar progressBar, float from, float to) {
        super();
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
        this.progress = progress;

        setInterpolator(new DecelerateInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);

        String newProgress = (int) value+"%";
        progress.setText(newProgress);
    }
}

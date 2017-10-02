package com.leduyhung.progresslibrary.progress.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by hungleduy on 10/1/17.
 */

public class ProgressCircleView extends SurfaceView implements Runnable {

    private SurfaceHolder holder;
    private Thread thread;
    private DrawProgressCircleHelper drawHelper;

    public ProgressCircleView(Context context) {
        super(context);
    }

    public ProgressCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressCircleView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void run() {

    }
}

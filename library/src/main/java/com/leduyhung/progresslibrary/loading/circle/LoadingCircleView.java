package com.leduyhung.progresslibrary.loading.circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.leduyhung.progresslibrary.R;

/**
 * Created by hungleduy on 9/29/17.
 */
public class LoadingCircleView extends SurfaceView implements Runnable {

    private int radius, loadingSize;
    private int borderColor;
    private int speed;

    private boolean isStop;

    private Thread thread;
    private SurfaceHolder holder;
    private DrawLoadingCircleHelper drawLoadingCircleHelper;

    public LoadingCircleView(Context context) {
        super(context);
        getAttribute(context, null);
    }

    public LoadingCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttribute(context, attrs);
    }

    public LoadingCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttribute(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(radius * 2 + radius / 5, radius * 2 + radius / 5);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        drawLoadingCircleHelper.setLocationDraw(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == VISIBLE) {

            if (thread == null && !isStop) {

                thread = new Thread(this);
                thread.start();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        isStop = true;
        if (thread != null)
            thread.interrupt();
        thread = null;
        holder = null;
    }

    @Override
    public void run() {

        while (!holder.getSurface().isValid() && !isStop)
            continue;

        runCircle();
    }

    private void getAttribute(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingCircleView);
            radius = typedArray.getDimensionPixelOffset(R.styleable.LoadingCircleView_loading_circle_radius, 0);
            loadingSize = typedArray.getDimensionPixelSize(R.styleable.LoadingCircleView_loading_circle_border_size, 0);
            borderColor = typedArray.getColor(R.styleable.LoadingCircleView_loading_circle_border_color, context.getResources().getColor(R.color.white));
            typedArray.recycle();
        }

        speed = 3;
        holder = getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
        drawLoadingCircleHelper = new DrawLoadingCircleHelper();
        drawLoadingCircleHelper.setBorderWidth(loadingSize);
        drawLoadingCircleHelper.setRadius(radius);
        drawLoadingCircleHelper.setColor(borderColor);
        isStop = true;
    }

    private void runCircle() {

        int progress = 20;
        int progressStart = 0;
        int seed = speed;
        int seed2 = seed * 3;
        Canvas c;
        while (progress <= 140 && !isStop && holder.getSurface().isValid()) {
            try {
                c = holder.lockCanvas();
                if (c != null) {
                    drawLoadingCircleHelper.drawLoading(c, progressStart, progress);
                    holder.unlockCanvasAndPost(c);
                    progressStart += seed2;
                    progress += seed;

                    if (progress >= 140) {
                        progress = 140;
                        seed = -seed;
                        seed2 = seed2 - seed;
                    } else if (progress <= 20) {

                        progress = 20;
                        seed = -seed;
                        seed2 = seed2 - seed;
                    }

                    if (progressStart >= 360) {

                        progressStart = 0;
                    }
                }
            } catch (Exception e) {

                // todo: Log crash
            }
        }

        if (thread != null)
            thread.interrupt();
        thread = null;
        if (holder.getSurface().isValid()) {
            c = holder.lockCanvas();
            if (c != null) {
                drawLoadingCircleHelper.closeDraw(c);
                holder.unlockCanvasAndPost(c);
            }
        }
        return;
    }

    public synchronized void showLoading(boolean show) {

        if (show) {

            if (isStop) {
                thread = new Thread(this);
                thread.start();
                isStop = false;
            }
        } else {

            thread = null;
            isStop = true;
        }
    }

    public boolean isShow() {

        return !isStop;
    }

    public void setLoadingColor(int color) {

        borderColor = color;
        drawLoadingCircleHelper.setColor(borderColor);
    }

    public void setLoadingSize(int radius) {

        this.radius = radius;
        drawLoadingCircleHelper.setRadius(radius);
    }
}
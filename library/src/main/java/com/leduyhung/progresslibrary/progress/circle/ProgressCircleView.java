package com.leduyhung.progresslibrary.progress.circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.leduyhung.progresslibrary.R;

/**
 * Created by hungleduy on 10/1/17.
 */
public class ProgressCircleView extends SurfaceView implements Runnable {

    private SurfaceHolder holder;
    private Thread thread;
    private DrawProgressCircleHelper drawHelper;

    private boolean isStop, hasName;
    private float radius, sizeProgress, sizeName, sizeValue;
    private int colorBackground, colorBackgroundProgress, colorProgress, colorValue, colorName;
    private int percent, totalPercent, percentRunning;
    private String nameProgress;

    public ProgressCircleView(Context context) {
        super(context);
        getAttribute(context, null);
    }

    public ProgressCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttribute(context, attrs);
    }

    public ProgressCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttribute(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension((int) (radius * 2 + sizeProgress + 2), (int) (radius * 2 + sizeProgress + 2));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        initView(0, 0, getWidth(), getHeight());
        if (thread != null && !thread.isAlive())
            thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHelper.drawConstruct(canvas, getWidth() / 2, getHeight() / 2);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == VISIBLE) {

            if (thread == null) {

                thread = new Thread(this);
                thread.start();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (thread != null)
            thread.interrupt();
        thread = null;
        holder = null;
    }

    @Override
    public void run() {

        while (!holder.getSurface().isValid() && !isStop)
            continue;

        runProgressAnimation();
        thread = null;
        isStop = true;
    }

    public void setPercent(int percent) {

        if (!isStop) {

            thread.interrupt();
        }

        thread = new Thread(this);
        this.percent = percent;
        calculatorTotalPercent();
        thread.start();
        isStop = false;
        invalidate();
    }

    public int getPercent() {

        return percent;
    }

    public boolean isRunning() {

        return !isStop;
    }

    public boolean isHasTitle() {

        return hasName;
    }

    private void getAttribute(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress_circle_view);
            radius = typedArray.getDimensionPixelSize(R.styleable.progress_circle_view_progress_circle_radius, 100);
            sizeProgress = typedArray.getDimensionPixelSize(R.styleable.progress_circle_view_progress_circle_size_progress, 5);
            colorBackground = typedArray.getColor(R.styleable.progress_circle_view_progress_circle_color_background, context.getResources().getColor(R.color.transparent));
            colorBackgroundProgress = typedArray.getColor(R.styleable.progress_circle_view_progress_circle_color_background_progress, context.getResources().getColor(R.color.blue));
            colorProgress = typedArray.getColor(R.styleable.progress_circle_view_progress_circle_color_progress, context.getResources().getColor(R.color.white));
            percent = typedArray.getInt(R.styleable.progress_circle_view_progress_circle_percent, 100);
            hasName = typedArray.getBoolean(R.styleable.progress_circle_view_progress_circle_has_name, false);
            nameProgress = typedArray.getString(R.styleable.progress_circle_view_progress_circle_text_name);
            sizeName = typedArray.getDimensionPixelSize(R.styleable.progress_circle_view_progress_circle_name_size, 30);
            sizeValue = typedArray.getDimensionPixelSize(R.styleable.progress_circle_view_progress_circle_value_size, 80);
            colorName = typedArray.getColor(R.styleable.progress_circle_view_progress_circle_color_name, context.getResources().getColor(R.color.blue));
            colorValue = typedArray.getColor(R.styleable.progress_circle_view_progress_circle_color_value, context.getResources().getColor(R.color.blue));
            typedArray.recycle();
        }

        holder = getHolder();
        setZOrderOnTop(true);
        setWillNotDraw(false);
        holder.setFormat(PixelFormat.TRANSPARENT);
        thread = new Thread(this);
        drawHelper = new DrawProgressCircleHelper();
        isStop = false;
    }

    private void initView(float left, float top, float right, float bottom) {

        drawHelper.setBackgroundColor(colorBackground);
        drawHelper.setBorderColor(colorBackgroundProgress);
        drawHelper.setLoadingColor(colorProgress);
        drawHelper.setNameColor(colorName);
        drawHelper.setValueColor(colorValue);
        drawHelper.setRadius(radius);
        drawHelper.setNameSize(sizeName);
        drawHelper.setValueSize(sizeValue);
        drawHelper.setHasName(hasName);
        drawHelper.setNameProgress(nameProgress);
        drawHelper.setBorderWith(sizeProgress);
        drawHelper.setLocationToDrawCircleAnimation(left, top, right, bottom);
        calculatorTotalPercent();
    }

    private void calculatorTotalPercent() {

        totalPercent = (percent * drawHelper.getDegress()) / 100;
        drawHelper.setPurposePercent(totalPercent);
    }

    private void calculatorPercentRunning() {

        if (percentRunning < totalPercent) {

            percentRunning += 3;
            if (totalPercent - percentRunning <= 1) {

                percentRunning = totalPercent;
            }
        } else if (percentRunning > totalPercent) {

            percentRunning -= 3;
            if (percentRunning - totalPercent <= 1) {

                percentRunning = totalPercent;
            }
        }
    }

    private void runProgressAnimation() {

        Canvas c;
        drawHelper.setAnimationComplete(false);
        while (holder.getSurface().isValid() && !isStop) {

            if (percentRunning != totalPercent) {
                c = holder.lockCanvas();
                if (c != null && holder.getSurface().isValid()) {

                    calculatorPercentRunning();
                    drawHelper.drawCircle(c, percentRunning);
                    holder.unlockCanvasAndPost(c);
                } else {

                    break;
                }
            } else {


                break;
            }
        }


        percentRunning = totalPercent;
        drawHelper.setAnimationComplete(true);
        postInvalidate();
        c = holder.lockCanvas();
        if (c != null && holder.getSurface().isValid()) {

            drawHelper.closeDraw(c);
            holder.unlockCanvasAndPost(c);
        }
        return;
    }
}
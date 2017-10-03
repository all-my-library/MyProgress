package com.leduyhung.progresslibrary.progress.circle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;

/**
 * Created by hungleduy on 10/1/17.
 */

class DrawProgressCircleHelper {

    private Paint paintBackground, paintBorder, paintProgress, paintName;
    private Path pathAnimation, pathContruct;
    private RectF rectF;

    private float radius;
    private float progressSize;
    private boolean hasName, animationComplete;
    private int degrees, startAngle;
    private int purposePercent;

    DrawProgressCircleHelper() {

        pathAnimation = new Path();
        pathContruct = new Path();
        rectF = new RectF();
        paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintProgress.setStyle(Paint.Style.STROKE);
        paintName = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintName.setTextAlign(Paint.Align.CENTER);
        paintName.setTextSize(20);
        animationComplete = false;
    }

    private void drawName(Canvas canvas, float x, float y) {

        if (hasName)
            canvas.drawText("Tiến trình", x, y * 2 - progressSize, paintName);
    }

    private void drawContructCircle(Canvas canvas, float x, float y) {

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.ADD);
        canvas.drawCircle(x, y, radius - (progressSize / 2), paintBackground);

        if (!hasName) {
            canvas.drawCircle(x, y, radius, paintBorder);
        } else {

            pathContruct.addArc(rectF, startAngle, 290);
            canvas.drawPath(pathContruct, paintBorder);
        }

        if (animationComplete) {

            pathAnimation.reset();
            pathAnimation.addArc(rectF, startAngle, purposePercent);
            canvas.drawPath(pathAnimation, paintProgress);
            drawValue(canvas);
            drawPercent(canvas);
        }
    }

    private void drawValue(Canvas canvas) {


    }

    private void drawPercent(Canvas canvas) {

    }

    void setHasName(boolean hasName) {

        this.hasName = hasName;

        if (hasName) {

            degrees = 290;
            startAngle = 125;
        } else {

            degrees = 360;
            startAngle = 270;
        }
    }

    int getDegress() {

        return degrees;
    }

    void setRadius(float radius) {
        this.radius = radius;
    }

    void setBorderWith(float progressSize) {

        this.progressSize = progressSize;
        paintProgress.setStrokeWidth(progressSize + 2);
        paintBorder.setStrokeWidth(progressSize);
    }

    void setBackgroundColor(int color) {

        paintBackground.setColor(color);
    }

    void setBorderColor(int color) {

        paintBorder.setColor(color);
        paintName.setColor(color);
    }

    void setLoadingColor(int color) {

        paintProgress.setColor(color);
    }

    void setLocationToDrawCircleAnimation(float left, float top, float right, float bottom) {

        rectF.set(left + ((right / 2) - radius), top + ((bottom / 2) - radius), right - ((right / 2) - radius), bottom - ((bottom / 2) - radius));
    }

    void setPurposePercent(int percent) {

        this.purposePercent = percent;
    }

    void setAnimationComplete(boolean isComplete) {

        this.animationComplete = isComplete;
    }

    void drawConstruct(Canvas canvas, float x, float y) {

        drawContructCircle(canvas, x, y);
        drawName(canvas, x, y);
    }

    void drawCircle(Canvas canvas, float progress) {

        pathAnimation.reset();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        pathAnimation.addArc(rectF, startAngle, progress);
        canvas.drawPath(pathAnimation, paintProgress);
        drawValue(canvas);
        drawPercent(canvas);
    }
}
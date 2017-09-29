package com.leduyhung.progresslibrary.loading.circle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;

/**
 * Created by hungleduy on 9/29/17.
 */
public class DrawLoadingCircleHelper {

    private Path path;
    private Paint paint;
    private RectF rectF;

    private int radius;

    DrawLoadingCircleHelper() {

        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        rectF = new RectF();
    }

    public void setColor(int color) {

        paint.setColor(color);
    }

    void setRadius(int radius) {

        this.radius = radius;
    }

    void setBorderWidth(int width) {

        paint.setStrokeWidth(width);
    }

    void setLocationDraw(float left, float top, float right, float bottom) {

        rectF.set(left + ((right / 2) - radius), top + ((bottom / 2) - radius), right - ((right / 2) - radius), bottom - ((bottom / 2) - radius));
    }

    void drawLoading(Canvas canvas, float progressStart, float progress) {

        path.reset();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        path.addArc(rectF, progressStart, progress);
        canvas.drawPath(path, paint);

        path.addArc(rectF, progressStart - 180, progress);
        canvas.drawPath(path, paint);
    }

    void closeDraw(Canvas canvas) {

        path.close();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
}
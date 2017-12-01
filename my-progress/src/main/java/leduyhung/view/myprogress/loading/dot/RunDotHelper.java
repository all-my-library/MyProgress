package leduyhung.view.myprogress.loading.dot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;

/**
 * Created by hungleduy on 9/29/17.
 */

class RunDotHelper {

    private DrawDotHelper[] drawDotHelpers;
    private int total;

    RunDotHelper(int radius, int total, int color, int distance) {

        this.total = total;
        drawDotHelpers = new DrawDotHelper[total];
        for (int i = 0; i < total; i++) {

            drawDotHelpers[i] = new DrawDotHelper(radius, color, 0 - ((distance * 2 * i) + (radius * i)));
        }
    }

    void setDotsColor(int color) {

        if (drawDotHelpers != null && drawDotHelpers.length > 0) {

            for (DrawDotHelper d : drawDotHelpers)
                d.setDotColor(color);
        } else {

            // TODO: show log message
        }
    }

    void runDot(Canvas canvas, float y, int width) {

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        for (int i = 1; i < total; i++) {

            drawDotHelpers[i].drawDot(canvas, y, width);
        }

        if (drawDotHelpers[drawDotHelpers.length - 1].checkDotRunComplete(width)) {

            for (int i = 1; i < total; i++) {

                drawDotHelpers[i].clearInfor();

            }
        }
    }

    void closeDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
}
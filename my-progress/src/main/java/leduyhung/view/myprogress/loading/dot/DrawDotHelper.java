package leduyhung.view.myprogress.loading.dot;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by hungleduy on 9/29/17.
 */

class DrawDotHelper {

    private Paint paint;
    private int raidus;
    int myX, oldX, speed, loopA;

    DrawDotHelper(int raidus, int color, int x) {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        this.raidus = raidus;
        this.myX = x;
        this.oldX = x;
        this.speed = 0;
        this.loopA = 0;
    }

    private int calculatorSpeed(int width) {

        int loop = loopA / 3;
        speed = ((2 * width) / 5) / 21;
        int rank = (2 * width) / 5;

        if (myX < 0) {

            speed = 3;
            if (myX + speed >= 0) {

                speed = -myX;
            }
        } else if (myX < rank) {

            if (myX == 0) {

                speed = ((2 * width) / 5) / 21;
                return speed;
            } else {

                speed -= loop;
                if (myX + speed > rank) {

                    speed = rank - myX;
                } else {

                    if (speed <= 2) {
                        speed = 3;
                    }
                }
            }
            loopA++;
        } else if (myX >= rank && myX <= ((3 * width) / 5)) {

            loopA = 0;
            speed = 3;
        } else {

            speed += loop;

            if (speed > ((2 * width) / 5) / 21)
                speed = ((2 * width) / 5) / 21;
            loopA++;
        }

        return speed;
    }

    boolean checkDotRunComplete(int width) {

        return myX > width;
    }

    void setDotColor(int color) {

        paint.setColor(color);
    }

    void drawDot(Canvas canvas, float y, int width) {

        canvas.drawCircle(myX, y, raidus, paint);
        myX += calculatorSpeed(width);
    }

    void clearInfor() {

        this.myX = oldX;
        this.speed = 0;
        this.loopA = 0;
    }
}
package leduyhung.view.myprogress.loading.dot;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import leduyhung.view.myprogress.R;

/**
 * Created by hungleduy on 9/29/17.
 */
public class LoadingDotView extends SurfaceView implements Runnable {

    private int radiusDot;
    private int dotColor;
    private int distance;
    private boolean isStop;

    private Thread thread;
    private SurfaceHolder holder;
    private RunDotHelper runDotHelper;

    public LoadingDotView(Context context) {
        super(context);
        getAttribute(context, null);
    }

    public LoadingDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttribute(context, attrs);
    }

    public LoadingDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttribute(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        initDotByWidth();
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

        while (holder != null && !holder.getSurface().isValid() && !isStop)
            continue;

        runDot();
        thread = null;
    }

    private void getAttribute(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingDotView);
            radiusDot = typedArray.getDimensionPixelOffset(R.styleable.LoadingDotView_loading_dot_radius, 0);
            distance = typedArray.getDimensionPixelOffset(R.styleable.LoadingDotView_loading_dot_distance, 20);
            dotColor = typedArray.getColor(R.styleable.LoadingDotView_loading_dot_color, context.getResources().getColor(R.color.white));
            typedArray.recycle();
        }

        holder = getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
        isStop = true;
    }

    private void initDotByWidth() {

        if (runDotHelper == null) {
            int width = getWidth();
            runDotHelper = new RunDotHelper(radiusDot, (width / (radiusDot * 4)) / 2, dotColor, distance);
        }
    }

    private void runDot() {

        int width = getWidth();
        int y = getHeight() / 2;
        Canvas c;

        while (!isStop && holder != null && holder.getSurface().isValid()) {

            try {
                c = holder.lockCanvas();
                if (c != null) {

                    runDotHelper.runDot(c, y, width);
                    holder.unlockCanvasAndPost(c);
                }
            } catch (Exception e) {

                // TODO: show log exception
            }
        }

        if (holder != null && holder.getSurface().isValid()) {
            c = holder.lockCanvas();
            if (c != null) {
                runDotHelper.closeDraw(c);
                holder.unlockCanvasAndPost(c);
            }
        }
        return;
    }

    public void showLoading(boolean show) {

        if (show && isStop) {
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
            isStop = false;
        } else {

            if (thread != null) {

                thread.interrupt();
                thread = null;
                isStop = true;
            }
        }
    }

    public boolean isShow() {

        return !isStop;
    }

    public void setDotColor(int color) {

        this.dotColor = color;
        runDotHelper.setDotsColor(dotColor);
    }
}
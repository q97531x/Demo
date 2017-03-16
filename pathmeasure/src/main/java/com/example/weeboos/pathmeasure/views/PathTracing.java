package com.example.weeboos.pathmeasure.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by weeboos on 2017/3/8.
 */

public class PathTracing extends View{

    private Path path;
    private Path dst;
    private Paint paint;
    private float mLength;
    private float mAnimValue;

    private PathMeasure pathMeasure;
    public PathTracing(Context context) {
        super(context);
    }

    public PathTracing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        dst = new Path();

        path.addCircle(400,400,100,Path.Direction.CW);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,true);

        mLength = pathMeasure.getLength();

        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimValue = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public PathTracing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dst.reset();
        dst.lineTo(0,0);

        float stop = mLength*mAnimValue;
        float start = (float)(stop-(0.5-Math.abs(mAnimValue-0.5))*mLength);

        pathMeasure.getSegment(start,stop,dst,true);
        canvas.drawPath(dst,paint);
    }
}

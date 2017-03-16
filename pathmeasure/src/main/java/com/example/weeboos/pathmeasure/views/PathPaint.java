package com.example.weeboos.pathmeasure.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by weeboos on 2017/3/8.
 */

public class PathPaint extends View {
    private Path path;
    private Path dst;
    private Paint paint;
    private float mLength;
    private float mAnimValue;
    private PathEffect mEffect;

    private PathMeasure pathMeasure;
    public PathPaint(Context context) {
        super(context);
    }

    public PathPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        dst = new Path();

        path.moveTo(100,100);
        path.lineTo(100,500);
        path.lineTo(400,300);
        path.close();

        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,true);

        mLength = pathMeasure.getLength();

        ValueAnimator animator = ValueAnimator.ofFloat(1,0);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimValue = (float)valueAnimator.getAnimatedValue();
                mEffect = new DashPathEffect(new float[]{mLength,mLength},mLength*mAnimValue);
                paint.setPathEffect(mEffect);

                invalidate();
            }
        });
        animator.start();
    }

    public PathPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dst.reset();
        canvas.drawPath(path,paint);
    }

}

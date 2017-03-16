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
 * Created by weeboos on 2017/3/9.
 */

public class PathPosView extends View implements View.OnClickListener{

    private Path mPath;
    private float[] mPos;
    private float[] mTan;
    private Paint paint;
    private PathMeasure pathMeasure;
    private float currentValue;

    private ValueAnimator animator;

    public PathPosView(Context context) {
        super(context);
    }

    public PathPosView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        mPath.addCircle(0,0,200,Path.Direction.CW);

        pathMeasure = new PathMeasure();
        pathMeasure.setPath(mPath,false);

        mPos = new float[2];
        mTan = new float[2];

        animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        setOnClickListener(this);
    }

    public PathPosView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathMeasure.getPosTan(currentValue*pathMeasure.getLength(),mPos,mTan);
        //角度弧度值
        float degree = (float)Math.atan2(mTan[1],mTan[0]);
        float degree1 = (float) ((float) degree*180/Math.PI);

        canvas.save();
        //移动画布
        canvas.translate(400,400);
        canvas.drawCircle(mPos[0],mPos[1],10,paint);
        canvas.drawPath(mPath,paint);
        canvas.rotate(degree1);
        canvas.drawLine(0,-200,300,-200,paint);

        canvas.restore();
    }

    @Override
    public void onClick(View view) {
        animator.start();
    }
}

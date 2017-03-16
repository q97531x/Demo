package com.example.weeboos.bezier.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.weeboos.bezier.BezierEvaluator;

/**
 * Created by weeboos on 2017/3/6.
 */

public class PathBezierView extends View implements View.OnClickListener{

    private int mStartPointX;
    private int mStartPointY;

    private int mEndPointX;
    private int mEndPointY;

    private int mFlagPointX;
    private int mFlagPointY;

    private Path path;

    private Paint paint,paintCircle;

    private int mMovePointX;
    private int mMovePointY;

    public PathBezierView(Context context) {
        super(context);
    }

    public PathBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(8);

        mStartPointX = 100;
        mStartPointY = 100;

        mMovePointX = mStartPointX;
        mMovePointY = mStartPointY;

        mEndPointX = 600;
        mEndPointY = 600;

        mFlagPointX = 500;
        mFlagPointY = 0;

        setOnClickListener(this);
    }

    public PathBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartPointX,mStartPointY,20,paintCircle);
        canvas.drawCircle(mEndPointX,mEndPointY,20,paintCircle);
        canvas.drawCircle(mMovePointX,mMovePointY,30,paintCircle);
        path.reset();
        path.moveTo(mStartPointX,mStartPointY);
        path.quadTo(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY);
        canvas.drawPath(path,paint);
    }

    @Override
    public void onClick(View view) {
        BezierEvaluator evaluator = new BezierEvaluator(new PointF(mFlagPointX,mFlagPointY));
        ValueAnimator animator = ValueAnimator.ofObject(evaluator,new PointF(mStartPointX,mStartPointY)
        ,new PointF(mEndPointX,mEndPointY));
        animator.setDuration(600);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                mMovePointX = (int)pointF.x;
                mMovePointY = (int)pointF.y;
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}

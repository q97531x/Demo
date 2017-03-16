package com.example.weeboos.bezier.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by weeboos on 2017/3/6.
 */

public class PathMorthingView extends View implements View.OnClickListener{
    //起始点坐标
    private float mStartPointX;
    private float mStartPointY;
    //终点坐标
    private float mEndPointX;
    private float mEndPointY;
    //控制点坐标
    private float mFlagAPointX;
    private float mFlagAPointY;
    private float mFlagBPointX;
    private float mFlagBPointY;

    private Path mPath;

    private Paint mPaintBezier, mPaintFlag, mPaintText;

    private boolean isSecondPoint = false;

    private ValueAnimator mValueAnimator;

    public PathMorthingView(Context context) {
        super(context);
    }

    public PathMorthingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.STROKE);

        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(3);
        mPaintBezier.setStyle(Paint.Style.STROKE);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setTextSize(20);

        setOnClickListener(this);
    }

    public PathMorthingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h / 2 - 200;

        mFlagAPointX = mStartPointX;
        mFlagAPointY = mStartPointY;
        mFlagBPointX = mEndPointX;
        mFlagBPointY = mEndPointY;
        mPath = new Path();

        mValueAnimator = ValueAnimator.ofFloat(mStartPointY,h);
        mValueAnimator.setInterpolator(new BounceInterpolator());
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mFlagAPointY = (float) valueAnimator.getAnimatedValue();
                mFlagBPointY = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);

        mPath.cubicTo(mFlagAPointX, mFlagAPointY, mFlagBPointX, mFlagBPointY, mEndPointX, mEndPointY);


        canvas.drawPoint(mStartPointX, mStartPointY, mPaintFlag);
        canvas.drawText("起点", mStartPointX, mStartPointY, mPaintText);
        canvas.drawPoint(mFlagAPointX, mFlagAPointY, mPaintFlag);
        canvas.drawText("控制点A", mFlagAPointX, mFlagAPointY, mPaintText);
        canvas.drawPoint(mFlagBPointX, mFlagBPointY, mPaintFlag);
        canvas.drawText("控制点B", mFlagBPointX, mFlagBPointY, mPaintText);
        canvas.drawPoint(mEndPointX, mEndPointY, mPaintFlag);
        canvas.drawText("终点", mEndPointX, mEndPointY, mPaintText);
        canvas.drawLine(mStartPointX, mStartPointY, mFlagAPointX, mFlagAPointY, mPaintFlag);
        canvas.drawLine(mFlagAPointX, mFlagAPointY, mFlagBPointX, mFlagBPointY, mPaintFlag);
        canvas.drawLine(mFlagBPointX, mFlagBPointY, mEndPointX, mEndPointY, mPaintFlag);

        canvas.drawPath(mPath, mPaintBezier);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                isSecondPoint = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isSecondPoint = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mFlagAPointX = event.getX();
                mFlagAPointY = event.getY();
                if(isSecondPoint){
                    mFlagBPointX = event.getX(1);
                    mFlagBPointY = event.getY(1);
                }
                invalidate();
                break;
        }
        return false;
    }*/

    @Override
    public void onClick(View view) {
        mValueAnimator.start();
    }
}

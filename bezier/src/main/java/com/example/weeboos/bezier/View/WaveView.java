package com.example.weeboos.bezier.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by weeboos on 2017/3/6.
 */

public class WaveView extends View implements View.OnClickListener{
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

    private int mWaveLength = 800;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mCenterY;
    private int mWaveCount;

    private boolean isSecondPoint = false;

    private ValueAnimator mValueAnimator;

    private int offset;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setColor(Color.RED);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setOnClickListener(this);
        mPath = new Path();
        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h/2;

        mWaveCount = (int)Math.round(mScreenWidth/mWaveLength + 1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLength+offset, mCenterY);

        for(int i = 0;i<mWaveCount;i++){
            mPath.quadTo(-mWaveLength*3/4+offset + i*mWaveLength,mCenterY+60,-mWaveLength/2+i*mWaveLength+offset,mCenterY);
            mPath.quadTo(-mWaveLength/4 +offset+ i*mWaveLength,mCenterY-60,i*mWaveLength+offset,mCenterY);
        }
        mPath.lineTo(mScreenWidth,mScreenHeight);
        mPath.lineTo(0,mScreenHeight);
        mPath.close();
//        mPath.cubicTo(mFlagAPointX, mFlagAPointY, mFlagBPointX, mFlagBPointY, mEndPointX, mEndPointY);

        /*canvas.drawPoint(mStartPointX, mStartPointY, mPaintFlag);
        canvas.drawText("起点", mStartPointX, mStartPointY, mPaintText);
        canvas.drawPoint(mFlagAPointX, mFlagAPointY, mPaintFlag);
        canvas.drawText("控制点A", mFlagAPointX, mFlagAPointY, mPaintText);
        canvas.drawPoint(mFlagBPointX, mFlagBPointY, mPaintFlag);
        canvas.drawText("控制点B", mFlagBPointX, mFlagBPointY, mPaintText);
        canvas.drawPoint(mEndPointX, mEndPointY, mPaintFlag);
        canvas.drawText("终点", mEndPointX, mEndPointY, mPaintText);
        canvas.drawLine(mStartPointX, mStartPointY, mFlagAPointX, mFlagAPointY, mPaintFlag);
        canvas.drawLine(mFlagAPointX, mFlagAPointY, mFlagBPointX, mFlagBPointY, mPaintFlag);
        canvas.drawLine(mFlagBPointX, mFlagBPointY, mEndPointX, mEndPointY, mPaintFlag);*/

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
        mValueAnimator = ValueAnimator.ofInt(0,mWaveLength);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                offset = (int)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }
}

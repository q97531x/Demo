package com.example.weeboos.bezier.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by weeboos on 2017/3/5.
 */

public class ThirdBezierView extends View {
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

    public ThirdBezierView(Context context) {
        super(context);
    }

    public ThirdBezierView(Context context, @Nullable AttributeSet attrs) {
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
    }

    public ThirdBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h / 2 - 200;

        mFlagAPointX = w / 2 - 100;
        mFlagAPointY = h / 2 - 500;
        mFlagBPointX = w / 2 + 100;
        mFlagBPointY = h / 2 - 500;
        mPath = new Path();
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

    @Override
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
        return true;
    }
}

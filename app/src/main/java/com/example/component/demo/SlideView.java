package com.example.component.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bo.wei on 2017/4/10.
 */

public class SlideView extends View{

    private Paint paint,tvPaint;

    private int mStartPointX;
    private int mStartPointY;

    private int mEndPointX;
    private int mEndPointY;

    private int mMovePointX;
    private int mMovePointY;

    private int width,height;

    private int l,r,t,b;

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);
        paint.setColor(Color.parseColor("#000000"));
        tvPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tvPaint.setStyle(Paint.Style.STROKE);
        tvPaint.setStrokeWidth(100);
        tvPaint.setColor(Color.parseColor("#ffffff"));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        l=left;
        t = top;
        r = right;
        b = bottom;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        mStartPointX = 0;

        mEndPointX = w;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,width,height,paint);
        canvas.drawText("nihao",width/2,height/2,tvPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}

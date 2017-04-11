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

    private Paint paint,tvPaint,paint1;

    private float mStartPointX;
    private int mStartPointY;

    private int mEndPointX;
    private int mEndPointY;

    private int mMovePointX;
    private int mMovePointY;

    private float distance = 0;     //滑动的距离
    private int variable = 1;

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
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(8);
        paint1.setColor(Color.parseColor("#66ccff"));
        tvPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tvPaint.setStyle(Paint.Style.STROKE);
        tvPaint.setStrokeWidth(5);
        tvPaint.setTextScaleX(30);
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


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0+variable*distance,0,width+distance,height,paint);
        canvas.drawRect(-width+distance,0,0+distance,height,paint1);
        canvas.drawText("nihao",0+distance,height/2,tvPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartPointX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float curX = event.getX();
                distance = curX - mStartPointX;
                if(distance>20) {
                    if(distance>=width){
                        distance = width;

                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}

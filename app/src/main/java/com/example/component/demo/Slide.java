package com.example.component.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bo.wei on 2017/4/10.
 */

public class Slide extends LinearLayout{
    private int width,height;
    private int l,r,t,b;
    private int pxWidth,pxHeight;
    private float downX;

    public Slide(Context context) {
        super(context);
    }

    public Slide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Slide(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.t = t;
        this.b = b;
        this.l = l;
        this.r = r;
        pxWidth = r-l;
        pxHeight = b-t;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.parseColor("#000000"));
        textView.setLayoutParams(new LayoutParams(width,height,1));
        textView.layout(l,t,r,b);
        addView(textView);
        TextView textView1 = new TextView(getContext());
        textView1.setBackgroundColor(Color.parseColor("#ffffff"));
        textView1.setLayoutParams(new LayoutParams(width,height,1));
        textView1.layout((int)2l-r,t,l,b);
        addView(textView1);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float curX = event.getX();
                float distance = curX-downX;
                getChildAt(0).layout((int)(l+distance),t,(int)(r+distance),b);
                getChildAt(1).layout((int)(2l-r+distance),t,(int)(l+distance),b);
                invalidate();
                break;
        }
        return true;
    }
}

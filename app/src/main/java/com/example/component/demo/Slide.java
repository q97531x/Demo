package com.example.component.demo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
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
    private TextView tv1,tv2;

    ValueAnimator animator;
    private boolean isChangePlace = false;
    private boolean idle = false;

    public Slide(Context context) {
        super(context);
    }

    public Slide(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Slide(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        tv1 = new TextView(getContext());
        tv1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        addView(tv1);
        tv2 = new TextView(getContext());
        tv2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        addView(tv2);

        animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int distance = (int)((float)animation.getAnimatedValue()*width);
                if((float)animation.getAnimatedValue()==1.0){
                    if(!isChangePlace) {
                        getChildAt(0).layout(l -r, t, l, b);
                        getChildAt(1).layout(l, t, r, b);
                    }else {
                        getChildAt(0).layout(l, t, r , b);
                        getChildAt(1).layout(l - r , t, l, b);
                    }
                }else {
                    if(!isChangePlace) {
                        getChildAt(0).layout(l + distance, t, r + distance, b);
                        getChildAt(1).layout(l - r + distance, t, l+distance, b);
                    }else {
                        getChildAt(0).layout(l -r + distance, t, l+distance, b);
                        getChildAt(1).layout(l+distance, t, r+distance, b);
                    }
                }
                invalidate();
            }
        });
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
        getChildAt(0).layout(l,t,r,b);
        getChildAt(1).layout(l-r,t,l,b);
        pxWidth = r-l;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

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
                float moveDistance = event.getX() - downX;
                if(moveDistance>40&&!idle){
                    isChangePlace = !isChangePlace;
                    idle = true;
                    animator.start();

                }
                break;
            case MotionEvent.ACTION_UP:
                downX = 0;
                idle = false;
                break;
        }
        return true;
    }
}

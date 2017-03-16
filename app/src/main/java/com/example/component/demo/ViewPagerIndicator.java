package com.example.component.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by bo.wei on 2017/3/16.
 */

public class ViewPagerIndicator extends LinearLayout{

    //画笔
    private Paint paint;
    //title数据
    private ArrayList<String> arrayList = new ArrayList<>();
    //ViewPager
    private ViewPager viewPager;
    //宽高
    private int width,height;

    public ViewPagerIndicator(Context context) {
        super(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    public void setViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorPrimary));


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0,100,width/3,300,20,20,paint);
    }
}

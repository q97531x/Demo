package com.example.component.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bo.wei on 2017/3/16.
 */

public class ViewPagerIndicator extends LinearLayout {

    //画笔
    private Paint paint;
    //title数据
    private ArrayList<String> arrayList = new ArrayList<>();
    //ViewPager
    private ViewPager viewPager;
    //宽高
    private int width,height;
    //偏移量
    private float offset = 0;
    //Tab数量
    private int mTabVisibleCount;

    public ViewPagerIndicator(Context context) {
        super(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setData(ArrayList<String> arrayList){
        this.arrayList = arrayList;


    }

    public void setViewPager(ViewPager Pager){
        this.viewPager = Pager;
        //监听viewPager的滑动事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                offset = width/3*(position+positionOffset);
                invalidate();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setStrokeWidth(5);

        mTabVisibleCount = 3;



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        //添加3个tab
        for (int i = 0;i<mTabVisibleCount;i++){
            final int j = i;
            TextView tv = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            lp.width = width / mTabVisibleCount;
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv.setText("hello");
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setLayoutParams(lp);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(j);
                }
            });
            addView(tv);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();

        canvas.drawRoundRect(offset,height-20,offset+width/3,height,20,20,paint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0,height/2,width,height/2,paint);
//        canvas.drawRoundRect(0,height-20,width/3,height,20,20,paint);
    }
}

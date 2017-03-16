package com.example.weeboos.bezier;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by weeboos on 2017/3/6.
 */

public class BezierEvaluator implements TypeEvaluator<PointF>{

    private PointF mFlagPoint;
    public BezierEvaluator(PointF flagPoint) {
        mFlagPoint = flagPoint;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.CalculateBezierPointForQuadratic(v,pointF,mFlagPoint,t1);
    }
}

package com.sample.simplerecyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

/**
 * Created by Jia on 2017/6/22.
 */

public class StickyDecoration extends RecyclerView.ItemDecoration{

    private GroupListener mListener;
    private int mHeight = 100;
    private Paint mPaint;
    private Paint mTextPaint;

    public static boolean isMoving = false;

    public StickyDecoration(GroupListener mListener) {
        this.mListener = mListener;

        mPaint = new Paint();
        mPaint.setColor(0XFF403F4F);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(0XFF000000);
        mTextPaint.setTextSize(mHeight - 40);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (!isMoving){
            super.onDrawOver(c, parent, state);
            Log.d("StickyDecoration", "onDrawOver: super.onDrawOver");
            drawDecoration(c, parent);
            Log.d("StickyDecoration", "onDrawOver: drawDecoration");
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if (pos == 0 || isFirstInGroup(pos)){
            outRect.top = mHeight;
        }
    }

    private void drawDecoration(Canvas c, RecyclerView parent){
        int left = parent.getLeft() + parent.getPaddingLeft();
        int right = parent.getRight() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        String preUserName;
        String currentUserName = null;
        for (int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(view);
            preUserName = currentUserName;
            currentUserName = getUserName(pos);
            if (currentUserName == null || TextUtils.equals(preUserName, currentUserName)){
                continue;
            }
            int bottom = Math.max(view.getTop(), mHeight);
            int top = bottom - mHeight;
            c.drawRect(left, top, right, bottom, mPaint);
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            float baseline = (bottom + top) / 2 - (fm.bottom + fm.top) / 2;
            String nickName;
            if (currentUserName.equals("狄仁杰")){
                nickName = "断案大师";
            } else {
                nickName = "王都密探";
            }
            c.drawText(nickName, left + 20, baseline, mTextPaint);

        }
    }

    private boolean isFirstInGroup(int pos) {
        if (pos == 0){
            return true;
        } else {
            String preName = getUserName(pos - 1);
            String currentName = getUserName(pos);
            return !TextUtils.equals(preName, currentName);
        }
    }

    private String getUserName(int pos) {

        if (mListener != null){
            return mListener.getUserName(pos);
        } else {
            return null;
        }
    }
}

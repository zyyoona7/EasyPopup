package com.zyyoona7.easypopup.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zyyoona7.easypopup.R;

/**
 * Created by zyyoona7 on 2017/8/2.
 */

public class TitleBar extends FrameLayout implements View.OnClickListener {

    private TextView mTitleText;
    private TextView mLeftText;
    private TextView mRightText;

    private OnTitleListener mOnTitleListener;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_title, this);
        mTitleText = (TextView) findViewById(R.id.tv_title_center);
        mLeftText = (TextView) findViewById(R.id.tv_title_left);
        mRightText = (TextView) findViewById(R.id.tv_title_right);
        mTitleText.setOnClickListener(this);
        mLeftText.setOnClickListener(this);
        mRightText.setOnClickListener(this);
    }

    public void setTile(String text) {
        mTitleText.setText(text);
    }

    public TextView getLeftView() {
        return mLeftText;
    }

    public TextView getRightView() {
        return mRightText;
    }

    public void showLeftText(boolean isShow) {
        if (isShow) {
            mLeftText.setVisibility(VISIBLE);
        } else {
            mLeftText.setVisibility(GONE);
        }
    }

    public void showRightText(boolean isShow) {
        if (isShow) {
            mRightText.setVisibility(VISIBLE);
        } else {
            mRightText.setVisibility(GONE);
        }
    }

    public void setOnTitleListener(OnTitleListener listener) {
        this.mOnTitleListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_left:
                if (mOnTitleListener != null) {
                    mOnTitleListener.onLeftClick(v);
                }
                break;
            case R.id.tv_title_center:
                if (mOnTitleListener != null) {
                    mOnTitleListener.onTitleClick(v);
                }
                break;
            case R.id.tv_title_right:
                if (mOnTitleListener != null) {
                    mOnTitleListener.onRightClick(v);
                }
                break;
        }
    }

    public interface OnTitleListener {

        /**
         * 左侧点击
         *
         * @param view
         */
        void onLeftClick(View view);

        /**
         * 右侧点击
         *
         * @param view
         */
        void onRightClick(View view);

        /**
         * title点击
         *
         * @param view
         */
        void onTitleClick(View view);
    }

}

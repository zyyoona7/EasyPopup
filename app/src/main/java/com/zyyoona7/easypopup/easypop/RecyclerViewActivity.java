package com.zyyoona7.easypopup.easypop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.BasePopup;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewActivity";

    private RecyclerPopAdapter mPopAdapter;
    private RecyclerView mRecyclerView;
    private EasyPopup mRvPop;

    private float mLastX;
    private float mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_pop);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mPopAdapter = new RecyclerPopAdapter();
        mPopAdapter.setNewData(list);
        mRecyclerView.setAdapter(mPopAdapter);
        initPop();
        initEvents();
    }

    private void initPop() {
        mRvPop = EasyPopup.create()
                .setContext(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.RightTopPopAnim)
//                .setHeight(700)
//                .setWidth(600)
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .apply();

        //回调在所有Show方法之后updateLocation方法之前执行
        //只有调用showAtAnchorView方法才会执行updateLocation方法
        mRvPop.setOnRealWHAlreadyListener(new BasePopup.OnRealWHAlreadyListener() {
            @Override
            public void onRealWHAlready(BasePopup basePopup, int popWidth, int popHeight, int anchorW, int anchorH) {
                Log.i(TAG, "onMeasureFinished: width=" + popWidth);
                int offsetX = (getResources().getDisplayMetrics().widthPixels - popWidth) / 2
                        - getResources().getDimensionPixelSize(R.dimen.dp_30);
                //重新设置偏移量
                mRvPop.setOffsetX(-offsetX);
            }
        });
    }

    private void initEvents() {
        mPopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int[] locations = new int[2];
                view.getLocationOnScreen(locations);
                Log.i(TAG, Arrays.toString(locations));
                if (locations[1] > getResources().getDisplayMetrics().heightPixels / 2) {
                    mRvPop.showAtAnchorView(view, YGravity.ABOVE, XGravity.LEFT);
                } else {
                    mRvPop.showAtAnchorView(view, YGravity.BELOW, XGravity.LEFT);
                }
            }
        });

        mPopAdapter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLastX = event.getRawX();
                    mLastY = event.getRawY();
                    LogUtils.i("onTouch x=" + mLastX + ",y=" + mLastY);
                }
                return false;
            }
        });

        mPopAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.i("onLongClick");
                // TODO: 2018/5/10 判断屏幕上下左右的边界来选择弹出方向
                mRvPop.showAtLocation(view, Gravity.NO_GRAVITY, (int) mLastX, (int) mLastY);
                return true;
            }
        });
    }
}

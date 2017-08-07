package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.lib.BaseCustomPopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyyoona7 on 2017/8/7.
 */

public class GiftPopup extends BaseCustomPopup {

    private RecyclerView mRecyclerView;

    protected GiftPopup(Context context) {
        super(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_gift);
        setHeight(SizeUtils.dp2px(200));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusAndOutsideEnable(true);
    }

    @Override
    protected void initViews(View view) {
        mRecyclerView = getView(R.id.rv_gift);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
        List<String> list = createList();
        GiftAdapter adapter = new GiftAdapter();
        adapter.setNewData(list);
        mRecyclerView.setAdapter(adapter);
    }

    private List<String> createList() {
        List<String> list = new ArrayList<>(1);
        for (int i = 0; i < 15; i++) {
            list.add("");
        }
        return list;
    }
}

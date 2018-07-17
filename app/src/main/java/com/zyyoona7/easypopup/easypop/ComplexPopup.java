package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.BasePopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyyoona7 on 2017/8/4.
 */

public class ComplexPopup extends BasePopup<ComplexPopup> {
    private static final String TAG = "ComplexPopup";

    private Button mOkBtn;
    private Button mCancelBtn;
    private RecyclerView mRecyclerView;
    private ComplexAdapter mComplexAdapter;
    private Context mContext;

    public static ComplexPopup create(Context context) {
        return new ComplexPopup(context);
    }

    protected ComplexPopup(Context context) {
        mContext = context;
        setContext(context);
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_complex, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(false)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);

    }

    @Override
    protected void initViews(View view, ComplexPopup basePopup) {
        mOkBtn = findViewById(R.id.btn_ok);
        mCancelBtn = findViewById(R.id.btn_cancel);
        mRecyclerView = findViewById(R.id.rv_complex);
        mComplexAdapter = new ComplexAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mComplexAdapter);
        List<String> list = new ArrayList<>(1);
        for (int i = 0; i < 5; i++) {
            list.add("烤肉盖饭");
        }
        mComplexAdapter.setNewData(list);

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mComplexAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_complex_delete:
                        mComplexAdapter.remove(position);
                        break;
                    default:

                }
            }
        });
    }

    public void setAbc() {

    }

}

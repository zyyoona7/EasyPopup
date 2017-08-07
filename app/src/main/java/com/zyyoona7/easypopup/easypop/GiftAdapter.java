package com.zyyoona7.easypopup.easypop;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyyoona7.easypopup.R;

/**
 * Created by zyyoona7 on 2017/8/7.
 */

public class GiftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public GiftAdapter() {
        super(R.layout.layout_item_gift, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
    }
}

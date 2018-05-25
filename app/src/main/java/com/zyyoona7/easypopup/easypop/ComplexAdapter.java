package com.zyyoona7.easypopup.easypop;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyyoona7.easypopup.R;

public class ComplexAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ComplexAdapter() {
        super(R.layout.item_complex, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_complex_item,item);
        helper.addOnClickListener(R.id.btn_complex_delete);
    }
}

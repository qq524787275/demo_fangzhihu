package com.fz.zhihunews.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fz.zhihunews.R;
import com.fz.zhihunews.activities.SectionActivity;
import com.fz.zhihunews.bean.Sections;

import java.util.List;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class SectionsAdapter extends BaseQuickAdapter<Sections.DataBean> {

    public SectionsAdapter(List<Sections.DataBean> data) {
        super(R.layout.item_sections, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Sections.DataBean dataBean) {
        baseViewHolder.setText(R.id.section_name, dataBean.getName());
        Glide.with(mContext).load(dataBean.getThumbnail()).into((ImageView) baseViewHolder.getView(R.id.section_thumbnail));
        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SectionActivity.class);
                intent.putExtra("id", dataBean.getId());
                mContext.startActivity(intent);
            }
        });
    }
}

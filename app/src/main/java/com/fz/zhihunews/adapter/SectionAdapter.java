package com.fz.zhihunews.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fz.zhihunews.R;
import com.fz.zhihunews.bean.Section;

import java.util.List;

/**
 * Created by feizhuo on 2017/4/28.
 */

public class SectionAdapter extends BaseQuickAdapter<Section.StoriesBean> {
    public SectionAdapter(List<Section.StoriesBean> data) {
        super(R.layout.item_latest, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Section.StoriesBean storiesBean) {
        baseViewHolder.setText(R.id.latest_title, storiesBean.getTitle());
        Glide.with(mContext).load(storiesBean.getImages().get(0)).into((ImageView) baseViewHolder.getView(R.id.latest_images));
    }
}

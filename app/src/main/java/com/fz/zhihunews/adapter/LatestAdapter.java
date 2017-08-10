package com.fz.zhihunews.adapter;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fz.zhihunews.R;
import com.fz.zhihunews.activities.NewsActivity;
import com.fz.zhihunews.bean.Latest;

import java.util.ArrayList;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class LatestAdapter extends BaseQuickAdapter<Latest.StoriesBean> {


    public LatestAdapter() {
        super(R.layout.item_latest, new ArrayList<Latest.StoriesBean>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Latest.StoriesBean storiesBean) {
        baseViewHolder.setText(R.id.latest_title, storiesBean.getTitle());

        Glide.with(mContext).load(storiesBean.getImages().get(0)).into((ImageView) baseViewHolder.getView(R.id.latest_images));

        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("id", storiesBean.getId());
                mContext.startActivity(intent);
            }
        });
    }
}

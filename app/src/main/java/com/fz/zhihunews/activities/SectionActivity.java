package com.fz.zhihunews.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseActivity;
import com.feeljoy.library.utils.ToastUtils;
import com.fz.zhihunews.Constants;
import com.fz.zhihunews.R;
import com.fz.zhihunews.adapter.SectionAdapter;
import com.fz.zhihunews.bean.Section;
import com.fz.zhihunews.callback.JsonConvert;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

public class SectionActivity extends BaseActivity {
    @ViewInject(id = R.id.recyclerview)
    private RecyclerView mRecyclerview;
    private List<Section.StoriesBean> list;
    private SectionAdapter sectionAdapter;
    private int id;
    @ViewInject(id = R.id.toolbar)
    private Toolbar mToolbar;

    @Override
    protected void fzOnCreate(Bundle arg0) {
        setContentView(R.layout.activity_section);
    }

    @Override
    protected void initView() {

        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
//显示返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
//设置返回按钮可点击
        actionBar.setHomeButtonEnabled(true);
//设置返回按钮图标
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//去掉原标题
        actionBar.setDisplayShowTitleEnabled(false);
//设置Logo

//设置主标题
        mToolbar.setTitle("专栏");
        mToolbar.setTitleTextColor(0xFFFFFFFF);

        id = getIntent().getIntExtra("id", -1);
        list = new ArrayList<>();
        sectionAdapter = new SectionAdapter(list);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(sectionAdapter);


       mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SectionActivity.this.finish();
           }
       });
    }

    @Override
    protected void initData() {
        downLoadData();
    }

    private void downLoadData() {

        if (id == -1) {
            ToastUtils.showShortToast("数据加载失败");
            return;
        }


        OkGo.<Section>get(Constants.URL_SECTION + id)
                .execute(new JsonConvert<Section>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<Section> response) {
                        super.onSuccess(response);
                        sectionAdapter.setNewData(response.body().getStories());
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<Section> response) {
                        super.onError(response);
                        ToastUtils.showShortToast("数据加载失败!");
                    }

                });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}

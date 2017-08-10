package com.fz.zhihunews.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseFragment;
import com.feeljoy.library.utils.ToastUtils;
import com.fz.zhihunews.Constants;
import com.fz.zhihunews.R;
import com.fz.zhihunews.adapter.SectionsAdapter;
import com.fz.zhihunews.bean.Sections;
import com.fz.zhihunews.callback.JsonConvert;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class ZhuanLanFragment extends BaseFragment {
    @ViewInject(id = R.id.recyclerview)
    private RecyclerView mRecyclerview;

    private List<Sections.DataBean> mDataBeen;
    private SectionsAdapter mSectionsAdapter;

    public static ZhuanLanFragment newInstance() {

        Bundle args = new Bundle();

        ZhuanLanFragment fragment = new ZhuanLanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_zhuanlan, container, false);
    }

    @Override
    protected void initView(View view) {
        mDataBeen = new ArrayList<>();
        mSectionsAdapter = new SectionsAdapter(mDataBeen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(gridLayoutManager);
        mRecyclerview.setAdapter(mSectionsAdapter);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData() {
        downLoadData();
    }

    private void downLoadData() {
        OkGo.<Sections>get(Constants.URL_ZHUANLAN)
                .execute(new JsonConvert<Sections>() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<Sections> response) {
                        super.onSuccess(response);
                        mSectionsAdapter.setNewData(response.body().getData());
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<Sections> response) {
                        super.onError(response);
                        ToastUtils.showLongToast("数据加载失败");
                    }

//                    @Override
//                    public void onSuccess(Sections sections, Call call, Response response) {
//                        super.onSuccess(sections, call, response);
//                        mSectionsAdapter.setNewData(sections.getData());
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        ToastUtils.showLongToast("数据加载失败");
//                    }
                });
    }

}

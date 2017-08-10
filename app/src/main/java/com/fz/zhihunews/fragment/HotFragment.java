package com.fz.zhihunews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feeljoy.library.base.BaseFragment;
import com.fz.zhihunews.R;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class HotFragment extends BaseFragment {
    public static HotFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_hot,container,false);
    }
}

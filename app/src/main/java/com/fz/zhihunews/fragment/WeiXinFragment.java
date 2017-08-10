package com.fz.zhihunews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseFragment;
import com.feeljoy.library.widget.dialog.FzProgressDialog;
import com.fz.zhihunews.R;
import com.fz.zhihunews.activities.BaiduActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class WeiXinFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(id = R.id.button)
    private Button mButton;
    @ViewInject(id = R.id.bt_baidu)
    private Button mBtBaidu;

    public static WeiXinFragment newInstance() {

        Bundle args = new Bundle();

        WeiXinFragment fragment = new WeiXinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_weixin, container, false);
    }

    @Override
    protected void initView(View view) {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FzProgressDialog fzProgressDialog = new FzProgressDialog(getActivity());
                fzProgressDialog.setTitle("友情提示!");
                fzProgressDialog.setShowMessage("正在加载....");
                fzProgressDialog.show();


                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        SystemClock.sleep(3000);
                        e.onNext("123");
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                fzProgressDialog.dismiss();
                            }
                        });
            }
        });

        mBtBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BaiduActivity.class);
                startActivity(intent);
            }
        });
    }

}

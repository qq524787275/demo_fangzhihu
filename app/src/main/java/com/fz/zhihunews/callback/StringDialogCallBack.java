package com.fz.zhihunews.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import com.lzy.okgo.callback.StringCallback;

/**
 * Created by feizhuo on 2017/4/25.
 */

public abstract class StringDialogCallBack extends StringCallback{
    private ProgressDialog progressDialog;

    public StringDialogCallBack(Activity activity) {
        progressDialog =new ProgressDialog(activity);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("友情提示");
        progressDialog.setMessage("请求网络中....");
    }

//
//    @Override
//    public void onBefore(BaseRequest request) {
//        super.onBefore(request);
//        if(progressDialog!=null && !progressDialog.isShowing()){
//            progressDialog.show();
//        }
//    }
//
//    @Override
//    public void onAfter(String s, Exception e) {
//        super.onAfter(s, e);
//        if(progressDialog!=null && progressDialog.isShowing()){
//            progressDialog.dismiss();
//        }
//    }

}

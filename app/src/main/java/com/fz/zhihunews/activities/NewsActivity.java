package com.fz.zhihunews.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseActivity;
import com.feeljoy.library.utils.ToastUtils;
import com.feeljoy.library.widget.popupwindow.FzPopupWindow;
import com.fz.zhihunews.Constants;
import com.fz.zhihunews.R;
import com.fz.zhihunews.bean.News;
import com.fz.zhihunews.callback.JsonConvert;
import com.lzy.okgo.OkGo;

import static com.fz.zhihunews.R.id.appBarLayout;

public class NewsActivity extends BaseActivity {


    @ViewInject(id = R.id.toolbar)
    private Toolbar mToolbar;
    @ViewInject(id = R.id.collapsing_toolbar)
    private CollapsingToolbarLayout mCollapsingToolbar;
    @ViewInject(id = appBarLayout)
    private AppBarLayout mAppBarLayout;
    @ViewInject(id = R.id.floatButton)
    private FloatingActionButton mFloatButton;
    @ViewInject(id = R.id.topImg)
    private ImageView mTopImg;
    private int id;
    @ViewInject(id = R.id.webView)
    private WebView mWebView;
    private static final String TAG = "NewsActivity";

    @Override
    protected void fzOnCreate(Bundle arg0) {
        setContentView(R.layout.activity_news);
    }

    @Override
    protected void initView() {

        //#27B443
        mCollapsingToolbar.setExpandedTitleColor(0xFFFFFFFF);
        mCollapsingToolbar.setCollapsedTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.this.finish();
            }
        });
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar=getSupportActionBar();
////显示返回按钮
//        actionBar.setDisplayHomeAsUpEnabled(true);
////设置返回按钮可点击
//        actionBar.setHomeButtonEnabled(true);
////设置返回按钮图标
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);


        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持JavaScript脚本语言
        settings.setAllowContentAccess(true);//允许访问文件
        settings.setBuiltInZoomControls(true);//设置显示缩放按钮
        settings.setSupportZoom(true);//设置支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);//屏幕自适应
        settings.setLoadWithOverviewMode(true);

        settings.setTextZoom(250);

        id = getIntent().getIntExtra("id", -1);

    }

    @Override
    protected void initEvent() {
        mFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("点击了");
                SpannableString sp=new SpannableString("退出当前账号");
                sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_f7443e)),0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                CharSequence[] title=new CharSequence[]{sp};
                new FzPopupWindow(NewsActivity.this, title, new FzPopupWindow.OnMenuClicktListener() {
                    @Override
                    public void onDismiss(FzPopupWindow actionSheet, boolean isCancel) {
                            ToastUtils.showShortToast("你点击了取消!");
                    }

                    @Override
                        public void onOtherButtonClick(FzPopupWindow actionSheet, int index) {
                            ToastUtils.showShortToast("你点击了退出!");
                    }
                }).show();
            }
        });


    }

    @Override
    protected void initData() {
        downLoadData();
    }

    private void downLoadData() {
        if (id == -1) {
            ToastUtils.showShortToast("数据加载出错");
            return;
        }
        OkGo.<News>get(Constants.URL_NEWS + id)
                .execute(new JsonConvert<News>() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<News> response) {
                        super.onSuccess(response);
                        News news=response.body();
                        Glide.with(NewsActivity.this).load(news.getImage()).into(mTopImg);
                        mCollapsingToolbar.setTitle(news.getTitle());

                        String css = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + news.getCss().get(0) + "\" />";
                        mWebView.loadDataWithBaseURL(null, css + news.getBody(), "text/html; charset=UTF-8", null, null);
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<News> response) {
                        super.onError(response);
                        ToastUtils.showShortToast("数据加载出错");
                    }

                });
    }
}

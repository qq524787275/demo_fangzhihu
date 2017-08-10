package com.feeljoy.library.widget.pulltorefresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.feeljoy.library.R;
import com.feeljoy.library.widget.pulltorefresh.animation.BaseAnimation;


/**
 * Description:上下拉刷新
 * Created by Cate on 2016/6/17.
 * Emial:liuh@80pm.com
 */
public class PulltoRefreshView extends FrameLayout {

    private SwipeRefreshLayout mRefresh;
    private RecyclerView mDisplay;

    private BaseRefreshQuickAdapter mAdapter;

    private boolean refreshEnable = true;

    private BaseRefreshQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener;


    public PulltoRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PulltoRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PulltoRefreshView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public PulltoRefreshView(Context context) {
        super(context);

        initView();
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.refresh,this);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mDisplay = (RecyclerView) findViewById(R.id.feeljoy_recyclerview);
    }


    public RecyclerView getRecyclerView(){
        return  mDisplay;
    }


    /**
     * 设置是否刷新
     * @param isRefresh
     */
    public void setRefreshing(boolean isRefresh){
        if (mRefresh!=null){
            mRefresh.setRefreshing(isRefresh);
        }
    }

    public boolean isRefreshing(){
        return mRefresh!=null&& mRefresh.isRefreshing();
    }


    public void setAdapter(BaseRefreshQuickAdapter adapter){
        if (mDisplay.getLayoutManager()==null){
            mDisplay.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        this.mAdapter = adapter;
        mDisplay.setAdapter(adapter);

    }

    public void addHeaderView(View header){
        if (mAdapter!=null){
            mAdapter.addHeaderView(header);
        }else {
//            throw  new Exception("please set adapter");
//            Log
        }

    }


    public void addFooterView(View footView){
        if (mAdapter!=null){
            mAdapter.addFooterView(footView);

        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout){
        mDisplay.setLayoutManager(layout);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        if (mRefresh!=null){
            mRefresh.setOnRefreshListener(onRefreshListener);
        }
    }

    /**
     * 添加分割线
     * @param itemDecoration
     */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        if (mDisplay!=null){
            mDisplay.addItemDecoration(itemDecoration);
        }
    }

    public void setOnLoadMoreListener(BaseRefreshQuickAdapter.RequestLoadMoreListener requestLoadMoreListener){
        this.mRequestLoadMoreListener = requestLoadMoreListener;
        if (mAdapter!=null)
            mAdapter.setOnLoadMoreListener(requestLoadMoreListener);
    }

    /**
     * 使RecyclerView保持固定的大小，该信息被用于自身的优化。
     * @param hasFixedSize
     */
    public void setHasFixedSize(boolean hasFixedSize){
        if (mDisplay!=null){
            mDisplay.setHasFixedSize(hasFixedSize);
        }
    }

    public void openLoadAnimation(){
        if(mAdapter!=null){
            mAdapter.openLoadAnimation();
        }
    }

    public  void openLoadAnimation(int animationType){
        if(mAdapter!=null){
            mAdapter.openLoadAnimation(animationType);

        }
    }

    public void openLoadAnimation(BaseAnimation animation){
        if(mAdapter!=null){
            mAdapter.openLoadAnimation(animation);

        }
    }


    public void openLoadMore(int pageSize, boolean enable) {
        if (mAdapter!=null){
            mAdapter.openLoadMore(pageSize,enable);
        }
    }


    /**
     * call the method before you should call setPageSize() method to setting up the enablePagerSize value,whether it will  invalid
     * enable the loading more data function if enable's value is true,or disable
     *
     * @param enable
     */
    public void openLoadMore(boolean enable) {
        if (mAdapter!=null){
            mAdapter.openLoadMore(enable);
        }
    }

    /**
     * setting up the size to decide the loading more data funcation whether enable
     * enable if the data size than pageSize,or diable
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if (mAdapter!=null){
            mAdapter.setPageSize(pageSize);
        }
    }

    public void setOnRecyclerViewItemClickListener(BaseRefreshQuickAdapter.OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mAdapter.setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener);
    }

    public void setOnRecyclerViewItemLongClickListener(BaseRefreshQuickAdapter.OnRecyclerViewItemLongClickListener onRecyclerViewItemLongClickListener) {
        mAdapter.setOnRecyclerViewItemLongClickListener(onRecyclerViewItemLongClickListener);
    }


    public void setOnRecyclerViewItemChildClickListener(BaseRefreshQuickAdapter.OnRecyclerViewItemChildClickListener childClickListener) {
        mAdapter.setOnRecyclerViewItemChildClickListener(childClickListener);
    }


    public boolean isRefreshEnable() {
        return refreshEnable;
    }

    public void setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;

        if (refreshEnable){
            mRefresh.setEnabled(refreshEnable);
        }

    }
}

package cn.edu.ustc.igank.ui.extend;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.ustc.igank.R;
import cn.edu.ustc.igank.database.cache.AndroidCache;
import cn.edu.ustc.igank.database.cache.ExtendCache;
import cn.edu.ustc.igank.support.BaseListFragment;
import cn.edu.ustc.igank.support.adapter.AndroidAdapter;
import cn.edu.ustc.igank.support.adapter.ExtendAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtendFragment extends BaseListFragment {


    public ExtendFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onCreateCache() {
        cache=new ExtendCache(handler);
    }

    @Override
    protected RecyclerView.Adapter bindAdapter() {
        return new ExtendAdapter(getContext(),cache);
    }

    @Override
    protected void refreshData() {
        cache.refresh();
    }

    @Override
    protected void loadMore() {
        cache.loadMore();
    }

    @Override
    protected void loadFromCache() {
        cache.loadFromCache();
    }

    @Override
    protected boolean hasData() {
        return cache.hasData();
    }

    @Override
    protected void getArgs() {

    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    }

}

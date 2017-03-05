package com.hannahxian.baselibrary.IOC;

import android.app.Activity;
import android.view.View;

/**
 * View 的findViewById的辅助类
 * Created by hannahxian on 2017/3/4.
 */

public class ViewFinder {
    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewFinder(View view) {
        this.mView = view;
    }

    public View findViewById(int viewId){
        return mActivity!=null?mActivity.findViewById(viewId):mView.findViewById(viewId);
    }
}

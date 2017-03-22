package com.hannahxian.baselibrary.navigationbar;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hannahxian on 2017/3/22.
 */

public abstract class AbsNavigationBar2<P extends AbsNavigationBar2.Builder.AbsNavigationParams> implements INavigationBar2 {

    private P mParams;

    private View mNavigaitionView;

    public AbsNavigationBar2(P mParams) {
        this.mParams = mParams;
        createAndBindView();
    }

    /**
     * bind and create view
     */
    private void createAndBindView() {
        //1.create view
        mNavigaitionView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(),mParams.mParent,false);
        //2.add view to parent
        mParams.mParent.addView(mNavigaitionView,0);

        applyView();
    }

    public P getParams() {
        return mParams;
    }

    protected void setText(int viewId,String text){
        TextView tv =  findViewById(viewId);
        if(TextUtils.isEmpty(text)){
            tv.setVisibility(View.GONE);
        }else
            tv.setText(text);
    }

    protected void setOnClickListner(int viewId, View.OnClickListener listener){
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
    }

    public <T extends View> T findViewById(int viewId){
        return (T) mNavigaitionView.findViewById(viewId);
    }

    public abstract static class Builder{
        AbsNavigationParams P;

        public Builder(Context context, ViewGroup parent){
            P = new AbsNavigationParams(context,parent);
        }

        public abstract AbsNavigationBar2 builder();

        public static class AbsNavigationParams{

            public Context mContext;

            public ViewGroup mParent;



            public AbsNavigationParams(Context context,ViewGroup parent){
                this.mContext = context;
                this.mParent = parent;
            }
        }
    }
}

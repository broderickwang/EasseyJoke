package com.hannahxian.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hannahxian.baselibrary.CommonRecycleAdapter.CommonRecyclerAdapter;
import com.hannahxian.baselibrary.CommonRecycleAdapter.MultiTypeSupport;
import com.hannahxian.baselibrary.CommonRecycleAdapter.ViewHolder;
import com.hannahxian.baselibrary.R;
import com.hannahxian.baselibrary.bean.ImageEntity;

import java.util.List;

/**
 * Created by hannahxian on 2017/5/6.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class SelectImageListAdapter extends CommonRecyclerAdapter<ImageEntity> {

    public SelectImageListAdapter(Context mContext, List mDatas) {
        super(mContext, mDatas, R.layout.select_image_adapter_layout);
    }

    public SelectImageListAdapter(Context context, List<ImageEntity> data, MultiTypeSupport<ImageEntity> multiTypeSupport) {
        super(context, data, multiTypeSupport);
    }

    @Override
    public void convert(ViewHolder holder, ImageEntity item) {
        if(item==null){
            //显示拍照

        }else{
            //显示图片
            ImageView imageView = holder.getView(R.id.adapter_img);
            Glide.with(mContext).load(item.path)
                    .centerCrop().into(imageView);
        }
    }

    @Override
    public int getLayoutId(Object item, int position) {
        return 0;
    }
}

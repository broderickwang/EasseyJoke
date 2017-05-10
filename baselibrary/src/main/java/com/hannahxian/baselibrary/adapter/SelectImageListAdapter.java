package com.hannahxian.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hannahxian.baselibrary.CommonRecycleAdapter.CommonRecyclerAdapter;
import com.hannahxian.baselibrary.CommonRecycleAdapter.MultiTypeSupport;
import com.hannahxian.baselibrary.CommonRecycleAdapter.ViewHolder;
import com.hannahxian.baselibrary.R;
import com.hannahxian.baselibrary.base.SelectImageActivity;
import com.hannahxian.baselibrary.bean.ImageEntity;
import com.hannahxian.baselibrary.listner.SelectImageListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannahxian on 2017/5/6.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class SelectImageListAdapter extends CommonRecyclerAdapter<ImageEntity> {

    private ArrayList<String> mResultImageList;

    private int mMax;

    private Context mContext;

    public SelectImageListAdapter(Context mContext, List mDatas) {
        super(mContext, mDatas, R.layout.select_image_adapter_layout);
    }

    public SelectImageListAdapter(Context context, List<ImageEntity> data,
                                  MultiTypeSupport<ImageEntity> multiTypeSupport,
                                  ArrayList<String> imageList,int max) {
        super(context, data, multiTypeSupport);
        this.mResultImageList = imageList;
        this.mMax = max;
        this.mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final ImageEntity item) {
        if(item==null){
            //显示拍照
            holder.setOnIntemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2017/5/10 拍照实现 6.0以上的权限问题

                }
            });
        }else{
            //显示图片
            ImageView imageView = holder.getView(R.id.adapter_img);

            CheckBox checkBox = holder.getView(R.id.img_checkBox);

            Glide.with(mContext).load(item.path)
                    .centerCrop().into(imageView);

            if(mResultImageList.contains(item.path)){
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }

            holder.setOnIntemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mResultImageList.contains(item.path)){
                        mResultImageList.remove(item.path);
                    }else{
                        if(mResultImageList.size()<mMax){
                            mResultImageList.add(item.path);
                        }else{
                            Toast.makeText(mContext, "最多选择"+mMax+"张图片", Toast.LENGTH_SHORT).show();
                        }
                    }

                    notifyDataSetChanged();

                    //通知显示布局的改变
                    if(mListner!=null)
                        mListner.select();
                }
            });

        }
    }

    @Override
    public int getLayoutId(Object item, int position) {
        return 0;
    }

    private SelectImageListner mListner;

    public void setListner(SelectImageListner mListner) {
        this.mListner = mListner;
    }
}

package com.hannahxian.baselibrary.bean;

import android.text.TextUtils;

/**
 * Created by hannahxian on 2017/5/6.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:图片对象
 */

public class ImageEntity {
    public String path;
    public String name;
    public long time;

    public ImageEntity(String path, String name, long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ImageEntity){
            ImageEntity compare = (ImageEntity)obj;
            return TextUtils.equals(this.path,compare.path);
        }
        return false;
    }
}

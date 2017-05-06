package com.hannahxian.easseyjoke.utils;

/**
 * Created by hannahxian on 2017/5/6.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class PatchUtils {
    /**
     *
     * @param oldAPKPath
     * @param newAPKPath
     * @param patchPath
     */
    public static native void combine(String oldAPKPath,String newAPKPath,String patchPath);
}

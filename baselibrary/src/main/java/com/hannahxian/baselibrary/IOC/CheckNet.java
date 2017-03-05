package com.hannahxian.baselibrary.IOC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * view 检测网络的Annotation
 * Created by hannahxian on 2017/3/4.
 */
//Annotation的位置 FIELD 属性 TYPE 类上 CONSTRUCTOR 构造函数
@Target(ElementType.METHOD)
//设置什么时候生效 CLASS 编译时 RUNTIME 运行时 SOURCE 源码
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNet {
}

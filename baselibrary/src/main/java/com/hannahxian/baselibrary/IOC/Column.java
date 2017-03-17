package com.hannahxian.baselibrary.IOC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Broderick on 2017/3/17.
 */
@Target(ElementType.FIELD)
//设置什么时候生效 CLASS 编译时 RUNTIME 运行时 SOURCE 源码
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String value();
}

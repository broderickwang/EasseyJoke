package com.hannahxian.baselibrary.IOC;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hannahxian on 2017/3/4.
 */

public class ViewUtils  {
    private static final String TAG = "ViewUtils";
    //目前使用
    public static void inject(Activity activity){
        inject(new ViewFinder(activity),activity);
    }
    //后面使用
    public static void inject(View view){
        inject(new ViewFinder(view),view);
    }
    public static void injcet(View view,Object object){
        inject(new ViewFinder(view),object);
    }

    //兼容上面的三个方法 object--反射需要执行的类
    private static void inject(ViewFinder finder,Object object){
        injectField(finder,object);
        injectEvent(finder,object);
    }

    //注入属性
    private static void injectField(ViewFinder finder, Object object) {
        //1.获取类里面的所有属性
        Class<?> clazz = object.getClass();
        //获取类中所有声明的属性
        Field[] fields = clazz.getDeclaredFields();
        //2.获取ViewById里面的value值
        for (Field field : fields){
            //获取设置的ViewById
            ViewById viewbyid = field.getAnnotation(ViewById.class);

            Column column = field.getAnnotation(Column.class);
            //如果ViewById不为空
            if(viewbyid != null){
                //获取注解里面的ID值
                int viewId = viewbyid.value();
                //3.findViewById找到view
                View view = finder.findViewById(viewId);

                //能够注入所有的修饰符  private public protected
                field.setAccessible(true);
                //4.动态的注入找到的view
                try {
                    field.set(object,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(column != null){
                String col = column.value();
                Log.i(TAG, "injectField: columnName="+col+" fileName = "+field.getName());
            }
        }



    }

    //事件注入
    private static void injectEvent(ViewFinder finder, Object object) {
        //1.获取类里面的方法
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        //2.获取onclick中的value值
        for(Method method : methods){
            OnClick onClick = method.getAnnotation(OnClick.class);
            if(onClick!=null){
                int[] viewIds = onClick.value();
                for(int viewId:viewIds){
                    //3.findview找到view
                    View view = finder.findViewById(viewId);

                    //扩展功能，检测网络
                    boolean isCheckNet = method.getAnnotation(CheckNet.class) != null;


                    if(null != view){
                        //4.setOnClickliSTNER
                        view.setOnClickListener(new DeclareOnClickListner(method,object,isCheckNet));
                    }
                }
            }
        }



    }

    private static class DeclareOnClickListner implements View.OnClickListener{
        private Object mObject;
        private Method mMethod;
        private boolean mIsCheckNet;

        public DeclareOnClickListner(Method method, Object object,boolean isCheckNet) {
            mObject = object;
            mMethod = method;
            mIsCheckNet = isCheckNet;
        }

        @Override
        public void onClick(View view) {
            //需不需要检测网络
            if(mIsCheckNet){
                if(!isNetworkAvailable(view.getContext())){
                    Toast.makeText(view.getContext(), "亲，请检查你的网络设置！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }


            //点击调用该方法
            try {
                mMethod.setAccessible(true);
                //5.反射执行方法
                mMethod.invoke(mObject,view);
            }  catch (Exception e) {
                e.printStackTrace();

                try {
                    Object[] obj = new Object[]{};
                    mMethod.invoke(mObject,obj);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}

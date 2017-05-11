# BaseLibarary

## 多布局Adapter

需要继承CommonRecyclerAdapter，重写构造方法和convert（）方法。

convert方法是adapter界面的回调实现

```Java
public class MyRecycleAdapter extends CommonRecyclerAdapter<String> {
	private int layoutId;
	public MyRecycleAdapter(Context context, List<String> data, MultiTypeSupport<String> multiTypeSupport) {
		super(context, data, multiTypeSupport);
	}


	public MyRecycleAdapter(Context mContext, List<String> mDatas, int mLayoutId) {
		super(mContext, mDatas, mLayoutId);
		this.layoutId = mLayoutId;
	}




	@Override
	public void convert(final ViewHolder holder, final String item) {
		holder.setText(R.id.txt_rec,item);
		/*holder.setOnIntemClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
			}
		});*/
	}

	@Override
	public int getLayoutId(Object item, int position) {
		return layoutId;
	}
}
```

MultiTypeSupport支持多布局的Adapter

## 可以添加头部和底部的Adapter

使用MyRecycleAdapter可以对recycleview添加头部和底部

调用addHeaderView和我addFooterView方法

```java
MyRecycleAdapter adapter = new MyRecycleAdapter(this,strs,R.layout.test_recycle);
		
WrapRecyclerAdapter adapter2 = new WrapRecyclerAdapter(adapter);
		adapter2.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_view,null,false));

mRecycleView.setAdapter(adapter2);
```

## 自定义Dialog

```java
 AlertDialog dialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.detail_comment_dialog)
                        .setText(R.id.txt,"自己设置的")
                        .setTitle("从底部弹出")
                        .fullWindow()
                        .fromBottom(false)
                        .addDefaultAnimation()
                        .show();

                final EditText com_et = dialog.getView(R.id.commont);
                // 如果setonclicklistner放到上面，获取不到，edittext的内容，以后可能会操作一些特殊的，比如listview  recyclelistview等，只能通过getview方法得到
                dialog.setOnClickListner(R.id.wb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity2.this, com_et.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
```

支持从底部弹出，自定义view，自定义事件等。

## 自定义状态栏

```java
 new DefaultNavigationBar
                        .Builder(this)
                        .setTitle("投稿")
                        .setRightText("发布")
                        .setRightClickListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity2.this, "ceshi", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .builder();
```

支持左右图标的设置，和事件的设置，左边默认是返回事件。



## 图片选择器

```java
ImageSelector.create().count(9).camera(true).multi().origenlist(al).start(TestActivity.this,1);
```



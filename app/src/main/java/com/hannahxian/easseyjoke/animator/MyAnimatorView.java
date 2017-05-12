package com.hannahxian.easseyjoke.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.hannahxian.easseyjoke.R;
import com.hannahxian.easseyjoke.mode.Point;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/12
 * Time: 15:30
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MyAnimatorView extends View {

	public static final float RADIUS = 50f;

	private Point mCurrentPoint;

	private Paint mPaint;

	private String mColor;

	public MyAnimatorView(Context context) {
		this(context,null);
	}

	public MyAnimatorView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MyAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.BLUE);
	}

	public void setColor(String mColor) {
		this.mColor = mColor;
		mPaint.setColor(Color.parseColor(mColor));
		invalidate();
	}

	public String getColor(){
		return this.mColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(mCurrentPoint == null){
			mCurrentPoint = new Point(RADIUS,RADIUS);
			drawCircle(canvas);
			startAnimator();
		}else {
			drawCircle(canvas);
		}
	}

	private void drawCircle(Canvas canvas){
		float x = mCurrentPoint.getX();
		float y = mCurrentPoint.getY();

		canvas.drawCircle(x,y,RADIUS,mPaint);
	}

	private void startAnimator(){
		Point startPoint = new Point(getWidth()/2, RADIUS);
		Point endPoint = new Point(getWidth()/2,getHeight()-RADIUS);

		ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				mCurrentPoint = (Point)animation.getAnimatedValue();
				invalidate();
			}
		});
		/*animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.REVERSE);*/
		/*animator.setDuration(10000);
		//动画重复
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.start();*/

		ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),
				"#0000FF","#FF0000");
	/*	objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
		objectAnimator.setRepeatMode(ValueAnimator.REVERSE);*/

		AnimatorSet set = new AnimatorSet();
		set.play(animator).with(objectAnimator);
		set.setInterpolator(new BounceInterpolator());
		set.setDuration(10000);
		set.start();


	}
}

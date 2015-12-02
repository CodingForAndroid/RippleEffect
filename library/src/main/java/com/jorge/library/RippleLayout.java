//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖保佑             永无BUG 
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//                  不见满街漂亮妹，哪个归得程序员？  
/************************************************************/
//  使用说明（introduction）
//  将这个布局作为任意 控件的 父布局  ，包裹 子布局， 子布局 则具有 5.0特性
//  须知   包裹的子布局 需要 是 enable　和clickable
//  若要 其执行完 绘制后 继续执行，则需 实现 OnRippleCompleteListener  后的 监听
//  eg:
//	RevealLayout findViewById = (RevealLayout) findViewById(R.id.layout1);
//		findViewById.setOnRippleCompleteListener(new OnRippleCompleteListener() {

//			@Override
//			public void onComplete() {
//				Intent  intent=new Intent(MainActivity.this, MainActivity.class);
//				startActivity(intent);

//			}
//		});
/************************************************************/
package com.jorge.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class RippleLayout extends LinearLayout {
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	// 圆心 坐标 x,y
	private float mCenterX, mCenterY;
	// 半径
	private int mRevealRadius = 0;
	private int[] mLocation = new int[2];
	private int INVALIDATE_DURATION = 4;
	// 目标的 宽度 和 高度
	private int mTargetHeight, mTargetWidth;
	// 圆的最大半径
	public int mMaxRadius;
	// 半径增加幅度
	private int mRevealRadiusGap;
	// 控件宽度
	private int mMinBetweenWidthAndHeight;
	// 是否按下状态
	private boolean mIsPressed;
	// 是否需要继续绘制
	private boolean mShouldDoAnimation;
	// 当前需要绘制的 view
	private View mTargetView;
	//手指按下与抬起 是在同一个View 上面。
	private boolean onOneView=true;
	public RippleLayout(Context context) {
		super(context);
		init();
	}

	public RippleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	// Android 3.0
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void init() {
//		 Debug.startMethodTracing("Love_World_");  
		// 设置onDraw 执行
//		setWillNotDraw(false);
		// 设置画笔颜色
		mPaint.setColor(getResources().getColor(R.color.reveal_color));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		this.getLocationOnScreen(mLocation);
	}

	/**
	 * 绘制 子view
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		// 如果目标view 不存在 或者 以及绘制完成 则取消绘制
		if (mTargetView == null || !mShouldDoAnimation || mTargetWidth <= 0)
			return;
		// 如果 圆的当前半径 超过了 按钮 宽度 或者高度的 1/2 则 半径增加幅度变大
		if (mRevealRadius > mMinBetweenWidthAndHeight /4)
			mRevealRadius += mRevealRadiusGap * 4;
		else
			mRevealRadius += mRevealRadiusGap;
		int[] location = new int[2];
		this.getLocationOnScreen(mLocation);
		mTargetView.getLocationOnScreen(location);
		// 计算当前目标view 的 l t r b
		int top = location[1] - mLocation[1];
		int left = location[0] - mLocation[0];
		int right = left + mTargetView.getMeasuredWidth();
		int bottom = top + mTargetView.getMeasuredHeight();
		canvas.save();
		// 设置绘制区域 起点（left,top）宽度 mTargetView.getMeasuredWidth()，高度
		// mTargetView.getMeasuredHeight()
		canvas.clipRect(left, top, right, bottom);
		// 画圆 圆心（mCenterX，mCenterY） 半径 ：mRevealRadius 画笔 ：mPaint
		canvas.drawCircle(mCenterX, mCenterY, mRevealRadius, mPaint);
		// 恢复原来的状态
		canvas.restore();
//		canvas.drawLine(startX, startY, stopX, stopY, paint)
		// 如果当前半径 还没有超过 最大半径 表示 还没有覆盖整个button 还需要继续 护自己
		if (mRevealRadius <= mMaxRadius) {
			postInvalidateDelayed(INVALIDATE_DURATION, left, top, right, bottom);
		} else if (!mIsPressed) {
			// 当绘制完成 时候执行， 让 button 恢复原来的样子
			mShouldDoAnimation = false;
			postInvalidateDelayed(INVALIDATE_DURATION, left, top, right, bottom);
			// 对外 实现 点击事件的效果，等button 刷新完成后执行
			if (onCompletionListener != null&&onOneView)
				onCompletionListener.onComplete(mTargetView.getId());
			
		}

	}

	// touch事件分发
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int x = (int) event.getRawX();
		int y = (int) event.getRawY();
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			
			//根据手指落下的位置，确定点击到哪个View身上
			targetView = getTargetView(this, x, y);
//			System.out.println("targetView:"+targetView.toString());
			if (targetView != null && targetView.isEnabled()) {
				mTargetView = targetView;
				initParametersForChild(event, targetView);
				postInvalidateDelayed(INVALIDATE_DURATION);
			}
			break;
		case MotionEvent.ACTION_UP:
			//判断手按下和抬起是在同一个View身上
			viewOnScreen(event, this,x,y);
			mIsPressed = false;
			postInvalidateDelayed(INVALIDATE_DURATION);
			break;
		case MotionEvent.ACTION_CANCEL:
			mIsPressed = false;
			postInvalidateDelayed(INVALIDATE_DURATION);
			break;
		}
		return super.dispatchTouchEvent(event);
	}

	/**
	 * 根据触摸到文字 获得 具体的 子view
	 * 
	 * @param view
	 * @param x
	 * @param y
	 * @return
	 */
	public View getTargetView(View view, int x, int y) {
		View target = null;
		ArrayList<View> views = view.getTouchables();
System.out.println("view:"+views.size());
		for (View child : views)
			
			if (isTouchPointInView(child, x, y)) {
				target = child;
				break;
			}
//		System.out.println("child:"+child.toString());
		return target;
	}

	/**
	 * 计算 x y 坐标 是否 在 child view 的范围内
	 * 
	 * @param child
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isTouchPointInView(View child, int x, int y) {
		int[] location = new int[2];
		child.getLocationOnScreen(location);
		int top = location[1];
		int left = location[0];
		int right = left + child.getMeasuredWidth();
		int bottom = top + child.getMeasuredHeight();

		if (child.isClickable() && y >= top && y <= bottom && x >= left && x <= right)
			return true;
		else
			return false;
	}

	/**
	 * 初始化子view 参数
	 * 
	 * @param event
	 * @param view
	 */
	public void initParametersForChild(MotionEvent event, View view) {
		// 手指的 x ,y 点在屏幕中的 坐标
		mCenterX = event.getX();
		mCenterY = event.getY();
		
		// 手机所在 view 自身的 宽度
		mTargetWidth = view.getMeasuredWidth();
		// 手机所在 view 自身的 高度
		mTargetHeight = view.getMeasuredHeight();
		// 判断 宽度 和高度 那个值比较大
		mMinBetweenWidthAndHeight = Math.min(mTargetWidth, mTargetHeight);
		mRevealRadius = 0;
		mRevealRadiusGap = mMinBetweenWidthAndHeight / 8;
		mIsPressed = true;
		mShouldDoAnimation = true;
		int[] location = new int[2];
		view.getLocationOnScreen(location);
		int left = location[0] - mLocation[0];
		int top = location[1] - mLocation[1];
		// view 距离左边界的宽度
		int mTransformedCenterX = (int) mCenterX - left;
		// view 距离顶部的距离
		int transformedCenterY = (int) mCenterY - top;
		// 根据 子view 的 宽度 和高度 获取 圆的 半径
		int maxX = Math.max(mTransformedCenterX, mTargetWidth - mTransformedCenterX);
		int maxY = Math.max(transformedCenterY, mTargetHeight - transformedCenterY);
		mMaxRadius = Math.max(maxX, maxY);

	}

	// 绘制完成后的 监听
	private OnRippleCompleteListener onCompletionListener;

	private View targetView;

	public void setOnRippleCompleteListener(OnRippleCompleteListener listener) {
		this.onCompletionListener = listener;
	}

	/**
	 * Defines a callback called at the end of the Ripple effect
	 */
	public interface OnRippleCompleteListener {
		void onComplete(int id);
	}
	
	/**
	 * 判断手抬起跟放下是不是同一个View
	 * @param event  手指抬起动作
	 * @param view   抬起的view
	 */
	public void viewOnScreen(MotionEvent event, View view,int x,int y){
		View upView = getTargetView(view, x, (int)y);

		if(targetView.equals(upView)&&(null!=upView)&&(targetView.getId()==upView.getId())){
			onOneView=true;
		}else{
			onOneView=false;
		}
	}
}

package com.test.graphics.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class ProgressView extends View{

	private Context mContext;

	private int red = 0xFFB94F4B;
	
	private int green = 0xFF4C6804;
	
	private int blue = 0xFF31B4C8;
	
	private int yellow = 0xFFCFA859;
	
	private MyPaint paint_red, paint_green, paint_blue, paint_yellow;
	
	private List<MyPaint> paints;
	
	/** 屏幕高度 */
	private int displayHeight;
	/** 屏幕宽度 */
	private int displayWidth;
	/** 屏幕宽度的中心位置*/
	private int centerWidth;
	
	private float strokeWidth = 20f;
	
	private int offsetX = 5;
	
	private int firstPosition = 0;
	
	private int count = 0;
	
	private int multi = 0;
	
	public ProgressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.init(context);
	}

	public ProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.init(context);
	}

	public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.init(context);
	}

	private void init(Context context){
		this.mContext = context;
		
		paints = new ArrayList<MyPaint>();
		
		paint_red = new MyPaint();
		paint_red.setAntiAlias(true);
		paint_red.setColor(red);
		paint_red.setStyle(Style.FILL);
		paint_red.setStrokeWidth(strokeWidth);
//		paints.add(paint_red);
		
		paint_green = new MyPaint();
		paint_green.setAntiAlias(true);
		paint_green.setColor(green);
		paint_green.setStyle(Style.FILL);
		paint_green.setStrokeWidth(strokeWidth);
//		paints.add(paint_green);
		
		paint_blue = new MyPaint();
		paint_blue.setAntiAlias(true);
		paint_blue.setColor(blue);
		paint_blue.setStyle(Style.FILL);
		paint_blue.setStrokeWidth(strokeWidth);
//		paints.add(paint_blue);
		
		paint_yellow = new MyPaint();
		paint_yellow.setAntiAlias(true);
		paint_yellow.setColor(yellow);
		paint_yellow.setStyle(Style.FILL);
		paint_yellow.setStrokeWidth(strokeWidth);
//		paints.add(paint_yellow);
		
		displayHeight = getResources().getDisplayMetrics().heightPixels;
		displayWidth = getResources().getDisplayMetrics().widthPixels;
		centerWidth = displayWidth / 2;
		multi = centerWidth / (offsetX * 3);
		
		this.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				new MyThread().start();
				getViewTreeObserver().removeOnPreDrawListener(this);
				return false;
			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		
//		System.out.println("onDraw--firstPosition-->" + firstPosition);
		
//		canvas.drawLine(0, 10, 500, 10, paint_red);
//		
//		canvas.drawLine(100, 10, 400, 10, paint_blue);
//		
//		canvas.drawLine(200, 10, 300, 10, paint_red);
		
		if(count == 0){
			count = count + 1;
			if(paints.contains(paint_red)){
				paints.remove(paint_red);
			}
			paint_red.setFlag(0);
			paints.add(paint_red);
		}else if(count == multi){
			count = count + 1;
			if(paints.contains(paint_green)){
				paints.remove(paint_green);
			}
			paint_green.setFlag(0);
			paints.add(paint_green);
		}else if(count == (multi * 2)){
			count = count + 1;
			if(paints.contains(paint_blue)){
				paints.remove(paint_blue);
			}
			paint_blue.setFlag(0);
			paints.add(paint_blue);
		}else if(count == (multi * 3)){
			count = count + 1;
			if(paints.contains(paint_yellow)){
				paints.remove(paint_yellow);
			}
			paint_yellow.setFlag(0);
			paints.add(paint_yellow);
		}else{
			count = count + 1;
			if(count == (multi * 4)){
				count = 0;
			}
		}
		
		for(int i = 0; i < paints.size(); i++){
			drawLine(canvas, i);
		}
//		drawLine(canvas, firstPosition);
	}
	
	
	private void drawLine(Canvas canvas, int position){
		System.out.println("drawLine--position-->" + position);
		int startX = centerWidth - offsetX * paints.get(position).getFlag();
		int stopX = centerWidth + offsetX * paints.get(position).getFlag();
		canvas.drawLine(startX, 0, stopX, 0, paints.get(position));
		paints.get(position).setFlag(paints.get(position).getFlag() + 1);
		
		/*当画笔绘制到屏幕外，重置在此画笔更外一层画笔绘制线的长度的因数为0，以实现重新从屏幕中心点centerWidth位置开始绘制*/
//		if(startX < 0){
//			if(position > 0){
//				MyPaint paint = paints.get(position - 1);
//				paint.setFlag(0);
//				paints.remove(position - 1);
//				paints.add(paint);
//			}
//		}
//		if(startX < -centerWidth / 3){
//			paints.get(position).setFlag(0);
//			paints.remove(position);
//		}
	}
	
	class MyThread extends Thread{
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(15);
					postInvalidate();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class MyPaint extends Paint{
		/** 记录标示（本程序主要用来记录画笔描绘的线的长度的倍数）*/
		private int flag = 0;

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}
	}
}

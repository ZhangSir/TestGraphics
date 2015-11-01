package com.test.graphics.view;

import android.R.color;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;

import com.test.graphics.R;

public class CircleView extends View{
	
	private int blackColor = 0x70000000; // �׺�ɫ
	private int whiteColor = 0xddffffff; // ��ɫ
	private int score;
	
	private Paint paint_black, paint_white, paint_text;
	private RectF rectf;
	private float tb;
	private float arc_y = 0f;
	private int score_text;
	

	public CircleView(Context context, int score) {
		super(context);
		this.score = score;
		this.initView();
	}
	
	private void initView(){
		
		Resources res = getResources();
		tb = res.getDimension(R.dimen.historyscore_tb);
		System.out.println("tb--------->" + tb);
		
		paint_black = new Paint();
		paint_black.setAntiAlias(true);
		paint_black.setColor(blackColor);
		paint_black.setStyle(Style.FILL);
		paint_black.setStrokeWidth(tb * 0.5f);
		
		paint_white = new Paint();
		paint_white.setAntiAlias(true);
		paint_white.setColor(whiteColor);
		paint_white.setStyle(Style.STROKE);
		paint_white.setStrokeWidth(tb * 1.0f);
		
		paint_text = new Paint();
		paint_text.setAntiAlias(true);
		paint_text.setColor(whiteColor);
		paint_text.setTextSize(tb * 6.0f);
		paint_text.setTextAlign(Align.CENTER);
//		paint_text.setStrokeWidth(tb * 0.1f);
//		paint_text.setStyle(Style.STROKE);
		
		rectf = new RectF();
		rectf.set(tb * 0.5f, tb * 0.5f, tb * 18.5f, tb * 18.5f);

		setLayoutParams(new LayoutParams((int) (tb * 19.5f), (int) (tb * 19.5f)));
		
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
		canvas.drawColor(Color.GRAY);
		canvas.drawArc(rectf, -90, 360, false, paint_black);
		canvas.drawArc(rectf, -90, arc_y, false, paint_white);
		
		/*���㵱���־�����ʾʱ��baseline;
		 * rectf.top + (rectf.bottom - rectf.top) / 2��õ�ֵ����Ҫ�������ֵľ�����������ߣ�
		 * FontMetrics.top����ֵ�Ǹ������������ֵ����������Ʊ߽絽baseline�ľ��룬
		 * ����(- fmi.top) - (fmi.bottom - fmi.top)/2 ��õ�ֵ��ָ���ֻ�����������ߵ�baseLine�ľ��룻
		 * ��������ֵ��Ӿ͵õ���baseLine����Ҫ�������ֵľ��������е�λ��*/
		FontMetricsInt fmi = paint_text.getFontMetricsInt();  
		float baseLine = rectf.top + (rectf.bottom - rectf.top) / 2  + (- fmi.top) - (fmi.bottom - fmi.top) / 2;
		
		canvas.drawText("" + score_text, tb * 9.7f, baseLine, paint_text);
	}

	class MyThread extends Thread{
		private int statek;
		int count;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				switch (statek) {
				case 0:
					try {
						Thread.sleep(200);
						statek = 1;
					} catch (InterruptedException e) {
					}
					break;
				case 1:
					try {
						Thread.sleep(15);
						arc_y += 3.6f;
						score_text++;
						count++;
						postInvalidate();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				if (count >= score)
					break;
			}
		}
		
	}
}

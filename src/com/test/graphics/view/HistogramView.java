package com.test.graphics.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.test.graphics.Score;

public class HistogramView extends View{

	private List<Score> listScores = null;
	private Paint paint_date, paint_rectf_gray, paint_rectf_blue;
	
	private int grayColor = 0x5faaaaaa;
	private int blueColor = 0xff00ffff;
	
	public HistogramView(Context context, List<Score> scores) {
		super(context);
		this.listScores = scores;
		init();
	}
	
	private void init(){
		if (null == listScores || listScores.size() == 0)
			return;

		paint_date = new Paint();
		paint_date.setStrokeWidth(10f);
		paint_date.setColor(grayColor);
		paint_date.setTextSize(40f);
		paint_date.setTextAlign(Align.CENTER);
		
		paint_rectf_gray = new Paint();
		paint_rectf_gray.setAntiAlias(true);
		paint_rectf_gray.setColor(grayColor);
		paint_rectf_gray.setStrokeWidth(10f);
		paint_rectf_gray.setStyle(Style.FILL);
		
		paint_rectf_blue = new Paint();
		paint_rectf_blue.setAntiAlias(true);
		paint_rectf_blue.setColor(blueColor);
		paint_rectf_blue.setStrokeWidth(10f);
		paint_rectf_blue.setStyle(Style.FILL);
		
		setLayoutParams(
				new LayoutParams(
						listScores.size() * 120,
						LayoutParams.MATCH_PARENT));
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (null == listScores || listScores.size() == 0)
			return;
		drawRectf(canvas);
		drawText(canvas);
	}
	
	private void drawRectf(Canvas canvas){
		RectF rf1 = new RectF();
		rf1.top = 0;
		rf1.bottom = getHeight() - 50;
		RectF rf2 = new RectF();
		for(int i = 0; i < listScores.size(); i++){
			rf1.left = 20 + 120 * i;
			rf1.right = 20 + 100 + 120 * i;
			canvas.drawRoundRect(rf1, 10, 10, paint_rectf_gray);
			
			rf2.left = 20 + 120 * i;
			rf2.right = 20 + 100 + 120 * i;
			rf2.top = (getHeight()- 50) - (listScores.get(i).score * ((getHeight() - 50)/100));
			rf2.bottom = getHeight() - 50;
			canvas.drawRoundRect(rf2, 10, 10, paint_rectf_blue);
			
		}
	}
	
	private void drawText(Canvas canvas){
		for(int i = 0; i < listScores.size(); i++){
			String score = String.valueOf(listScores.get(i).score);
			String date = listScores.get(i).date;
			String date_1 = date
					.substring(date.indexOf("-") + 1, date.length());
			canvas.drawText(score, 40 + 20 + 120 * i, getHeight() - 10, paint_date);
		}
	}

}

package com.test.graphics;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.test.graphics.view.CircleView;
import com.test.graphics.view.DiagramView;
import com.test.graphics.view.HistogramView;
import com.test.graphics.view.ProgressView;

public class MainActivity extends Activity {

	private ProgressView pView;
	
	private LinearLayout llayoutCircle;
	private RelativeLayout rlayoutHistogram;
	private RelativeLayout rlayoutCurve;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pView = (ProgressView) this.findViewById(R.id.pv_main);
		llayoutCircle = (LinearLayout) this.findViewById(R.id.llayout_circle);
		rlayoutHistogram = (RelativeLayout) this.findViewById(R.id.rlayout_histogram);
		rlayoutCurve = (RelativeLayout) this.findViewById(R.id.rlayout_curve);
		
		llayoutCircle.addView(new CircleView(this, 90)); 
		
		llayoutCircle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent();
				it.setClass(MainActivity.this, CalendarActivity.class);
				startActivity(it);
			}
		});
		
		List<Score> list = new ArrayList<Score>();;//Öù×´Í¼  ·¶Î§10-100
		for (int i = 0; i < 28; i++) {
			Score s = new Score();
			s.date = "2013-10-" + i;
			s.score = getRandom(10,100);
			list.add(s);
		}
		rlayoutHistogram.addView(new HistogramView(this, list));
		
		
		List<Integer> lists = new ArrayList<Integer>();//ÏßÐÔÍ¼  ·¶Î§10-100
		for (int i = 0; i < 48; i++) {
			if (i < 8 || i == 28 || i == 12 || i == 18 || i == 20 || i == 30
					|| i == 34) {
				lists.add(0);
			} else {
				lists.add(getRandom(0, 500));
			}
		}
		rlayoutCurve.addView(new DiagramView(this, lists));
	}
	
	public int getRandom(int min,int max){
		return (int) Math.round(Math.random()*(max-min)+min);
	}
}

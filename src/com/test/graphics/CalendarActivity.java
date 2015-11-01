package com.test.graphics;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.test.graphics.view.calendar.CalendarLayout;
import com.test.graphics.view.calendar.CalendarView.OnDateSelectedListener;
import com.test.graphics.view.calendar.Cell;

public class CalendarActivity extends Activity {

	private CalendarLayout mCalendarLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
		mCalendarLayout = (CalendarLayout) this.findViewById(R.id.cl_practice3_search);

		mCalendarLayout.setOnDateSelectedListener(new OnDateSelectedListener() {
			
			@Override
			public void onSelected(Cell cell) {
				// TODO Auto-generated method stub
				Toast.makeText(CalendarActivity.this, cell.getDay() + "", Toast.LENGTH_SHORT).show();
			}
		});
	}
}

package com.test.graphics.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class SampleView extends View {  
    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG  
            | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG  
            | Canvas.CLIP_TO_LAYER_SAVE_FLAG;  

    private Paint mPaint;  

    public SampleView(Context context) {  
        super(context);  
        setFocusable(true);  

        mPaint = new Paint();  
        mPaint.setAntiAlias(true);  
    }  

    @Override  
    protected void onDraw(Canvas canvas) {  
    	 
    	canvas.translate(100, 100);  
        canvas.drawColor(Color.RED);//���Կ�����������Ļ��Ȼ���Ϊ��ɫ  
          
        canvas.drawRect(new Rect(-100, -100, 0, 0), new Paint());
        
        canvas.save();
        
        canvas.scale(0.5f, 0.5f);  //������ 
        canvas.drawRect(new Rect(0, 0, 100, 100), new Paint());  
          
        canvas.restore();
        
//        canvas.scale(2.0f, 2.0f);  //�ָ�������
        canvas.translate(200, 0);  
        canvas.rotate(30);  //��ת��  
        canvas.drawRect(new Rect(0, 0, 100, 100), new Paint());
          
//        canvas.saveLayerAlpha(0, 0, 190, 190, 0x88, LAYER_FLAGS); //�����µ�ͼ��
//        canvas.drawColor(Color.YELLOW);
        canvas.translate(200, 0);  
        canvas.skew(0.5f, 0.5f);//Ť����  
        canvas.drawRect(new Rect(0, 0, 100, 100), new Paint());  
    	
    	
//        canvas.drawColor(Color.WHITE);    
//        canvas.translate(10, 10);    
//        mPaint.setColor(Color.RED);    
//        canvas.drawCircle(75, 75, 75, mPaint);    
//        
//        canvas.saveLayerAlpha(0, 0, 190, 190, 0x88, LAYER_FLAGS); //�����µ�ͼ��
//        
//        canvas.drawColor(Color.YELLOW);
//        canvas.translate(10, 10);
//        mPaint.setColor(Color.BLUE);    
//        canvas.drawCircle(75, 75, 75, mPaint);    
//        canvas.restore();  
     }  
}  
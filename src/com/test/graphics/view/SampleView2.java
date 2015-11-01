package com.test.graphics.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.View;

public class SampleView2 extends View {  
    private Paint mPaint;  
    private Path mPath;  

    public SampleView2(Context context) {  
        super(context);  
        setFocusable(true);  

        mPaint = new Paint();  
        mPaint.setAntiAlias(true);  
        mPaint.setStrokeWidth(6);  
        mPaint.setTextSize(16);  
        mPaint.setTextAlign(Paint.Align.RIGHT);  

        mPath = new Path();  
    }  

    private void drawScene(Canvas canvas) {  
        canvas.clipRect(0, 0, 100, 100);  

        canvas.drawColor(Color.WHITE);  

        mPaint.setColor(Color.RED);  
        canvas.drawLine(0, 0, 100, 100, mPaint);  

        mPaint.setColor(Color.GREEN);  
        canvas.drawCircle(30, 70, 30, mPaint);  

        mPaint.setColor(Color.BLUE);  
        canvas.drawText("Clipping", 100, 30, mPaint);  
    }  

    /**
     * 与clipRect和clipPath要使用当前的matrix进行变换不同。clipRegion不会进行转换。也就是说canvas的matrix对clipRegion没有影响。
     * 实际测试结果clipRegion也会进行转换
     * @param canvas
     */
    private void drawClip1(Canvas canvas){
    	Paint paint=new Paint();  
    	 canvas.scale(0.5f, 0.5f);  
    	 canvas.save();  
    	 canvas.clipRect(new Rect(100,100,200,200));//裁剪区域实际大小为50*50  
    	 canvas.drawColor(Color.RED);  
    	 canvas.restore();  
    	   
    	 canvas.drawRect(new Rect(0,0,100,100), paint);//矩形实际大小为50*50  
    	   
    	 canvas.clipRegion(new Region(new Rect(300,300,400,400)));//裁剪区域实际大小为100*100  
    	 canvas.drawColor(Color.BLACK);  
    }
    
    /**
     * API Demo
     * @param canvas
     */
    private void drawClip2(Canvas canvas){
    	canvas.save();  
        canvas.translate(10, 10);  
        drawScene(canvas);  
        canvas.restore();  

        canvas.save();  
        canvas.translate(160, 10);  
        canvas.clipRect(10, 10, 90, 90);  
//        canvas.drawColor(Color.WHITE);
        canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);  
//        canvas.drawColor(Color.BLACK);
        drawScene(canvas);  
        canvas.restore();  

        canvas.save();  
        canvas.translate(10, 160);  
        mPath.reset();  
        canvas.clipPath(mPath); // makes the clip empty  
        mPath.addCircle(50, 50, 50, Path.Direction.CCW);  
        canvas.clipPath(mPath, Region.Op.REPLACE); 
//        canvas.drawColor(Color.BLACK);
        drawScene(canvas);  
        canvas.restore();  

        canvas.save();  
        canvas.translate(160, 160);  
        canvas.clipRect(0, 0, 60, 60);  
        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);  
        drawScene(canvas);  
        canvas.restore();  

        canvas.save();  
        canvas.translate(10, 310);  
        canvas.clipRect(0, 0, 60, 60);  
        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);  
        drawScene(canvas);  
        canvas.restore();  

        canvas.save();  
        canvas.translate(160, 310);  
        canvas.clipRect(0, 0, 60, 60);  
        canvas.clipRect(40, 40, 100, 100, Region.Op.REVERSE_DIFFERENCE);  
        drawScene(canvas);  
        canvas.restore();  
    }
    
    @Override  
    protected void onDraw(Canvas canvas) {  
        canvas.drawColor(Color.GRAY);  

        drawClip2(canvas);
    }  
      
}

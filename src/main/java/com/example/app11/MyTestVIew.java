package com.example.app11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * autour : lbing
 * date : 2018/8/2 0002 14:37
 * className :
 * version : 1.0
 * description :
 */


public class MyTestVIew extends View {

    private Paint mPaint;
    public MyTestVIew(Context context) {
        this(context,null);
    }

    public MyTestVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTestVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#909009"));
        mPaint.setDither(true);
        mPaint.setTextSize(60);
    }

//    VertexMode  顶点类型    比如他是三角形（连续3个顶点）或者 四边形  （连续4个顶点）等等
//    vertexCount  顶点数   总共有多少个顶点绘制。
//    verts[]     顶点数组  [0,0,0,1,1,0,...]  前面有xy 3组 如果是类型是三角形 他就构成一个三角形的绘制基元，往后类推。
//    vertOffset   顶点数据 起始位置  可能全部绘制，也可能只绘制部分顶点。与 vertexCount 配置使用 一般为0
//    texs[]        纹理数组  就是对图片等进行采样，然后去渲染顶点。（这个比较复杂，需要了解下 比如opengl渲染原理。）
//    texOffset   同上offset  就是偏移量
//    colors[]     颜色数组  直接用颜色渲染顶点
//    colorOffset 同上offset  就是偏移量
//    indices[]    顶点索引 可能只绘制部分顶点 这个就是存放那些顶点的index ，  即verts[index]
//    indexOffset    同上offset  就是偏移量
//    indexCount    绘制多少个索引点。
//    paint         这个只有 texs[] 不空 必须提供， 提供图片之类东西 采样。


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("ttt",100,100,mPaint);

//        canvas.drawVertices(Canvas.VertexMode.TRIANGLE_FAN,
//                5,
//                new float[]{1,0,0,0,0,
//                            2,2,2,2,2},
//                0,
//                new float[]{},
//                0,
//                new int[]{Color.parseColor("#0f0f0f")},
//                0,
//                new short[]{},
//                0,
//                0,
//                mPaint);


//        float[] pts={500,100,250,500,  //表示的是起点和终点
//                250,500,800,200,
//                800,200,200,200,
//                200,200,750,500,
//                750,500,500,100};
//        canvas.drawLines(pts,mPaint);
//
//        RectF rect=new RectF(100,800,300,1000);
//        canvas.drawRoundRect(rect,100,50,mPaint);
//
//        RectF rectF = new RectF(100,1100,300,1300);
//        canvas.drawArc(rectF,30,90,false,mPaint);


        Path path=new Path();
        path.moveTo(200,1000);
        path.lineTo(300,800);
        path.lineTo(400,800);
        path.lineTo(500,1100);
        path.close();
        canvas.drawTextOnPath("兵哥最漂亮!!!啦啦啦啦啦啦",path,10,10,mPaint);

    }
}

package com.norden.nvs_tp_a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

public class GraphDraw {
    private final int W=1080;
    private final int H=1080;

    private Bitmap bm = Bitmap.createBitmap(W, H, Bitmap.Config.ARGB_8888);
    GraphDraw(ArrayList<Integer> data){
        if(data==null||data.size()<1){
            return;
        }
        while(data.size()<600){
            data.add(0);
        }
        bm.eraseColor(Color.parseColor("#22000000"));
        Paint pnt=new Paint();
        Canvas canvas=new Canvas(bm);

        Path pth = new Path();
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setStrokeWidth( 4);
        pnt.setColor(Color.parseColor("#FF66FF88"));
        pnt.setTextSize(30);

        int factor=W/data.size();
        factor++;
        int x=0;
        pth.moveTo(x, 0);
        for (int i=0;i<data.size();i++) {
            pth.lineTo((factor*i), W-(float)(data.get(i)) );
        }


        canvas.drawPath(pth, pnt);
        canvas.drawText("-1000",20,H-1000,pnt);
        canvas.drawText("-900",20,H-900,pnt);
        canvas.drawText("-800",20,H-800,pnt);
        canvas.drawText("-700",20,H-700,pnt);
        canvas.drawText("-600",20,H-600,pnt);
        canvas.drawText("-500",20,H-500,pnt);
        canvas.drawText("-400",20,H-400,pnt);
        canvas.drawText("-300",20,H-300,pnt);
        canvas.drawText("-200",20,H-200,pnt);
        canvas.drawText("-100",20,H-100,pnt);


        int yValue=25;
        canvas.drawText("|",50*factor,yValue,pnt);
        canvas.drawText("|",100*factor,yValue,pnt);
        canvas.drawText("|",150*factor,yValue,pnt);
        canvas.drawText("|",200*factor,yValue,pnt);
        canvas.drawText("|",250*factor,yValue,pnt);
        canvas.drawText("|",300*factor,yValue,pnt);
        canvas.drawText("|",350*factor,yValue,pnt);
        canvas.drawText("|",400*factor,yValue,pnt);
        canvas.drawText("|",450*factor,yValue,pnt);
        canvas.drawText("|",500*factor,yValue,pnt);
        canvas.drawText("|",550*factor,yValue,pnt);


        canvas.drawText("100",100*factor,yValue+40,pnt);
        canvas.drawText("200",200*factor,yValue+40,pnt);
        canvas.drawText("300",300*factor,yValue+40,pnt);
        canvas.drawText("400",400*factor,yValue+40,pnt);
        canvas.drawText("500",500*factor,yValue+40,pnt);


        canvas.drawText("size: "+data.size(),W-150,130,pnt);


    }

    Bitmap getGraph(){
        return bm;
    }
}

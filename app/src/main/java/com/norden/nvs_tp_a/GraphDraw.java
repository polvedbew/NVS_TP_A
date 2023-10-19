package com.norden.nvs_tp_a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;

public class GraphDraw {
    private final int W=1080;
    private final int H=1080;

    private Bitmap bm = Bitmap.createBitmap(W, H, Bitmap.Config.ARGB_8888);
    GraphDraw(List<Integer> data){
        if(data==null||data.size()<1){
            return;
        }
        bm.eraseColor(Color.parseColor("#22000000"));
        Paint pnt=new Paint();
        Canvas canvas=new Canvas(bm);

        Path pth = new Path();
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setStrokeWidth( 4);
        pnt.setColor(Color.parseColor("#FFAAFFAA"));
        pnt.setTextSize(50);

        int factor=W/data.size();
        factor++;
        int x=0;
        pth.moveTo(x, 0);
        for (int i=0;i<data.size();i++) {
            pth.lineTo(factor*i, W-(float)data.get(i) );
        }

        canvas.drawPath(pth, pnt);
        canvas.drawText("-100-",20,W-100,pnt);
        canvas.drawText("-"+(H-80)+"-",20,100,pnt);

    }

    Bitmap getGraph(){
        return bm;
    }
}

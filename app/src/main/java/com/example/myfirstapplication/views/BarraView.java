package com.example.myfirstapplication.views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Record;
import com.example.myfirstapplication.db.daos.DaoRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BarraView extends View {
    public float mWidth;
    public float mHeight;
    private Paint mBarPaint;
    private Paint mTextPaint;
    List<Record> totalRecord = new ArrayList<>();

    public BarraView(Context context) {
        super(context);
        init();
    }

    public BarraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private float mayorValor(){
        float mayor = 0;

        for(int i=0; i<totalRecord.size(); i++){
            if(totalRecord.get(i).getTotalGrams() > mayor){
                mayor = totalRecord.get(i).getTotalGrams();
            }
            if(totalRecord.get(i).getTotalScore() > mayor){
                mayor = totalRecord.get(i).getTotalScore();
            }
        }
        return mayor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float segment = 16;
        float parteH = mHeight - (mHeight / segment) * (segment - 1);
        float parteW = mWidth - (mWidth / segment) * (segment - 1);
        float heightF = mHeight - 2 * parteH;
        float widthF = mWidth - 2 * parteW;

        float mayorValor = mayorValor();
        float valorSuperior = (float) Math.ceil(mayorValor / 5) * 5;
        int escala = (int) valorSuperior / 5;


        float division = heightF / 5;
        int nLineas = 6;

        mTextPaint.setStrokeWidth(3f);
        mTextPaint.setColor(Color.parseColor("#C8C8C8"));

        for (int j = 0; j < totalRecord.size(); j++) {
            canvas.drawLine(parteW, (heightF + parteH) - (division * j), widthF + parteW, (heightF + parteH) - (division * j), mTextPaint);
        }
        canvas.drawLine(parteW, (heightF + parteH), parteW, parteH, mTextPaint);
        canvas.drawLine(widthF + parteW, heightF + parteH, widthF + parteW, parteH, mTextPaint);

        mTextPaint.setColor(Color.parseColor("#000000"));
        mTextPaint.setTextSize(35f);
        for (int i = 0; i < nLineas; i++) {
            String medida = String.valueOf(i * escala);
            canvas.drawText(medida, parteW / 4, (heightF + parteH) - (division * i), mTextPaint);
        }

        String colores[] = {"#00FCE1", "#636161"};

        for(int i=0; i<totalRecord.size(); i++){

            float heightV = parteH + division * ((valorSuperior - totalRecord.get(i).getTotalGrams())/ escala);
            float heightV2 = parteH + division * ((valorSuperior - totalRecord.get(i).getTotalScore())/ escala);

            float startW = (widthF/totalRecord.size());
            float widthV = parteW + startW * (i + 1);
            float startQ = (startW/10)*5;
            float startD = (startW/10)*2;

            mBarPaint.setStrokeWidth((startW/10)*4);
            float center1 = widthV - startQ - startD;
            float center2 = widthV - startQ + startD;

            mBarPaint.setColor(Color.parseColor(colores[0]));
            canvas.drawLine(center1,heightF+parteH,center1, heightV, mBarPaint);
            mBarPaint.setColor(Color.parseColor(colores[1]));
            canvas.drawLine(center2, heightF+parteH,center2, heightV2, mBarPaint);

            float textPos;
            if(totalRecord.size() > 6)
                textPos = widthV - (startW/10)*8;
            else if(totalRecord.size() > 3)
                textPos = widthV - (startW/10)*7;
            else
                textPos = widthV - (startW/10)*6;
            String date = month(totalRecord.get(i).getMonth()-1) + " " + totalRecord.get(i).getDay();
            canvas.drawText(date, textPos,heightF+parteH+(parteH/2), mTextPaint);
        }
    }

    private String month(int m) {
        String months[] = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
        return months[m];
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    private void init(){
        totalRecord.add(new Record(1,22,12,2022,10,2));
//        totalRecord.add(new Record(1,2,1,1,2,1));
//        totalRecord.add(new Record(1,3,1,1,3,1));
//        totalRecord.add(new Record(1,4,1,1,1,1));
//        totalRecord.add(new Record(1,5,1,1,50,1));
//        totalRecord.add(new Record(1,6,1,1,6,1));
//        totalRecord.add(new Record(1,6,1,1,6,1));

        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoRecord daoRecord = db.daoRecord();

        int diasMeses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        int diaMes, month, year;
        Calendar calendar = Calendar.getInstance();
        Record recordQuery;
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        for(int i=0; i<7; i++){
            diaMes = calendar.get(Calendar.DAY_OF_MONTH)-i;
            if(diaMes == 0){
                diaMes = diasMeses[calendar.get(Calendar.MONTH-1)];
                month = calendar.get(Calendar.MONTH) - 1;
            }
            recordQuery = daoRecord.getRecordByDate(diaMes,month,year);
            if(recordQuery!=null){
                totalRecord.add(recordQuery);
            }
        }

//        totalRecord = daoRecord.getRecords();

        mBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(10);
    }
}

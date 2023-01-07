package com.example.pets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;

public class BitmapConvert {
    public static byte[] getByteArrayfromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }
    public static Bitmap getBitmapfromByteArray(byte[] bitmap) {
        return BitmapFactory.decodeByteArray(bitmap , 0, bitmap.length);
    }
    public static Bitmap bitmapCrop(Bitmap b) {
        int origWidth = b.getWidth();
        int origHeight = b.getHeight();
        Bitmap b2=Bitmap.createScaledBitmap(b,origWidth/5,origHeight/5,false);
        return b2;
    }
    public static Bitmap bitmapIncrease(Bitmap b){
        int origWidth = b.getWidth();
        int origHeight = b.getHeight();
        Bitmap b2=Bitmap.createScaledBitmap(b,origWidth*5,origHeight*5,true);
        return b2;
    }
    public static Bitmap bitmapRotate(Bitmap b){
        int origWidth = b.getWidth();
        int origHeight = b.getHeight();
        if (origWidth>origHeight){
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            return Bitmap.createBitmap(b, 0, 0, origWidth, origHeight, matrix, true);
        }
        return b;
    }
}

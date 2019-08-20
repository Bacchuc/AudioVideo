package com.bacchus.audiovideo.draw_picture

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import com.bacchus.audiovideo.R

class CustomView(context: Context?) : View (context){

    private val paint = Paint()
    var bitmap: Bitmap

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.draw_picture_ic_flowers)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)
    }
}
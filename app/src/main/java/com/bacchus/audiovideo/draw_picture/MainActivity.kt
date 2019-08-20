package com.bacchus.audiovideo.draw_picture

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import com.bacchus.audiovideo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setImageView()
        setSurfaceView()
    }

    fun setImageView() {
        iv.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.draw_picture_ic_flowers))
    }

    fun setSurfaceView() {
        sv.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                val paint = Paint()
                paint.isAntiAlias = true
                paint.style = Paint.Style.STROKE

                val canvas = surfaceHolder.lockCanvas()
                canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.draw_picture_ic_flowers), 0f, 0f, paint)

                surfaceHolder.unlockCanvasAndPost(canvas)
            }

            override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

            }

            override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {

            }
        })
    }

    fun setCustomView(){

    }
}

// Ejercicio 15: ImageView que permite acercar o alejar la imagen con el gesto de pellizco (Pinch)
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio15Activity : AppCompatActivity() {

    private lateinit var imgZoom: ImageView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio15)

        imgZoom = findViewById(R.id.imgZoom)

        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                // Limitar la escala entre 0.5x y 5.0x
                scaleFactor = scaleFactor.coerceIn(0.5f, 5.0f)
                imgZoom.scaleX = scaleFactor
                imgZoom.scaleY = scaleFactor
                return true
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}

// Ejercicio 18: Controlar una imagen con Arrastre, Pinch (escalar) y Rotación
package com.example.hilosejecucinsensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2

class Ejercicio18Activity : AppCompatActivity() {

    private lateinit var imgTransformar: ImageView
    private lateinit var scaleDetector: ScaleGestureDetector

    private var scaleFactor = 1.0f
    private var lastX = 0f
    private var lastY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio18)

        imgTransformar = findViewById(R.id.imgTransformar)

        scaleDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = scaleFactor.coerceIn(0.5f, 4.0f)
                imgTransformar.scaleX = scaleFactor
                imgTransformar.scaleY = scaleFactor
                return true
            }
        })

        imgTransformar.setOnTouchListener { view, event ->
            scaleDetector.onTouchEvent(event)

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = event.rawX - view.x
                    lastY = event.rawY - view.y
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.pointerCount == 1) {
                        // Arrastre
                        view.x = event.rawX - lastX
                        view.y = event.rawY - lastY
                    } else if (event.pointerCount >= 2) {
                        // Rotación
                        val deltaX = (event.getX(1) - event.getX(0)).toDouble()
                        val deltaY = (event.getY(1) - event.getY(0)).toDouble()
                        val degrees = Math.toDegrees(atan2(deltaY, deltaX)).toFloat()
                        view.rotation = degrees
                    }
                }
            }
            true
        }
    }
}

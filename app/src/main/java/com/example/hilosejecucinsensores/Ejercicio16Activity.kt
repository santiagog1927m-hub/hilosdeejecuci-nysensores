// Ejercicio 16: Vista que gira según la posición de dos dedos (cálculo de ángulo)
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2

class Ejercicio16Activity : AppCompatActivity() {

    private lateinit var imgGirar: ImageView
    private lateinit var tvAngulo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio16)

        imgGirar = findViewById(R.id.imgGirar)
        tvAngulo = findViewById(R.id.tvAngulo)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.pointerCount >= 2) {
            val deltaX = (event.getX(1) - event.getX(0)).toDouble()
            val deltaY = (event.getY(1) - event.getY(0)).toDouble()
            val radians = atan2(deltaY, deltaX)
            val degrees = Math.toDegrees(radians).toFloat()

            imgGirar.rotation = degrees
            tvAngulo.text = "Ángulo: ${degrees.toInt()}°"
        }
        return super.onTouchEvent(event)
    }
}

// Ejercicio 12: Usar GestureDetector para reconocer Toque simple, Toque prolongado y Deslizamiento rápido
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio12Activity : AppCompatActivity() {

    private lateinit var tvGesto: TextView
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio12)

        tvGesto = findViewById(R.id.tvGesto)

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                tvGesto.text = "Toque simple (Tap)"
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                tvGesto.text = "Toque prolongado (Long press)"
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                tvGesto.text = "Deslizamiento rápido (Fling)"
                return true
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}

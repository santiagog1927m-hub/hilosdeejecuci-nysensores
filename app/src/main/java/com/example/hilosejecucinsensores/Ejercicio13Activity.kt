// Ejercicio 13: Detectar cuántos dedos hay en pantalla y mostrar sus posiciones x e y en tiempo real
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio13Activity : AppCompatActivity() {

    private lateinit var tvDedos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio13)
        tvDedos = findViewById(R.id.tvDedos)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val count = event.pointerCount
        val sb = StringBuilder("Cantidad de dedos: $count\n\n")

        for (i in 0 until count) {
            val pointerId = event.getPointerId(i)
            val x = event.getX(i).toInt()
            val y = event.getY(i).toInt()
            sb.append("Dedo ID $pointerId -> X: $x, Y: $y\n")
        }

        tvDedos.text = sb.toString()
        return super.onTouchEvent(event)
    }
}

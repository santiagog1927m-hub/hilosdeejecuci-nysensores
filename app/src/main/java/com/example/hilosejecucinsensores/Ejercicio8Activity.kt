// Ejercicio 8: Mostrar coordenadas x e y del punto de toque en un TextView
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio8Activity : AppCompatActivity() {

    private lateinit var tvCoordenadas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio8)
        tvCoordenadas = findViewById(R.id.tvCoordenadas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        tvCoordenadas.text = "X: $x, Y: $y"
        return super.onTouchEvent(event)
    }
}

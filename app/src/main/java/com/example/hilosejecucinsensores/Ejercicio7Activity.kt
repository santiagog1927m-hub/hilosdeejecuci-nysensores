// Ejercicio 7: TextView con mensajes según evento táctil (ACTION_DOWN, ACTION_MOVE, ACTION_UP)
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio7Activity : AppCompatActivity() {

    private lateinit var tvEvento: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio7)
        tvEvento = findViewById(R.id.tvEvento)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> tvEvento.text = "Pulsación iniciada"
            MotionEvent.ACTION_MOVE -> tvEvento.text = "Desplazando el dedo"
            MotionEvent.ACTION_UP -> tvEvento.text = "Pulsación finalizada"
        }
        return super.onTouchEvent(event)
    }
}

// Ejercicio 9: Vista personalizada que cambia su color de fondo al tocarla
package com.example.hilosejecucinsensores

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class ColorChangeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        setBackgroundColor(Color.LTGRAY)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            // Cambiar color a un valor aleatorio al tocar
            val randomColor = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            setBackgroundColor(randomColor)
            return true
        }
        return super.onTouchEvent(event)
    }
}

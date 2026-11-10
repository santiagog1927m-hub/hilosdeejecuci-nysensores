// Ejercicio 14: Vista para dibujar trazos simultáneos con varios dedos en diferentes colores
package com.example.hilosejecucinsensores

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultiTouchDrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW)
    private val activePaths = HashMap<Int, Path>()
    private val activePaints = HashMap<Int, Paint>()
    private val completedPaths = mutableListOf<Pair<Path, Paint>>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((path, paint) in completedPaths) {
            canvas.drawPath(path, paint)
        }
        for (pointerId in activePaths.keys) {
            val path = activePaths[pointerId] ?: continue
            val paint = activePaints[pointerId] ?: continue
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val actionIndex = event.actionIndex
        val pointerId = event.getPointerId(actionIndex)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                val paint = Paint().apply {
                    color = colors[pointerId % colors.size]
                    isAntiAlias = true
                    strokeWidth = 10f
                    style = Paint.Style.STROKE
                }
                activePaths[pointerId] = path
                activePaints[pointerId] = paint
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val id = event.getPointerId(i)
                    activePaths[id]?.lineTo(event.getX(i), event.getY(i))
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                val path = activePaths.remove(pointerId)
                val paint = activePaints.remove(pointerId)
                if (path != null && paint != null) {
                    completedPaths.add(Pair(path, paint))
                }
            }
        }
        invalidate()
        return true
    }
}

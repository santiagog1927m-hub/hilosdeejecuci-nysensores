// Ejercicio 20: Intensidad de luz ambiental en lux y cambio de color de fondo (Sensor.TYPE_LIGHT)
package com.example.hilosejecucinsensores

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio20Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private lateinit var layoutRaiz: View
    private lateinit var tvLuz: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio20)

        layoutRaiz = findViewById(R.id.layoutRaiz)
        tvLuz = findViewById(R.id.tvLuz)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        val lux = event.values[0]
        tvLuz.text = "Luz: $lux lux"

        // Cambiar color de fondo según la luz
        when {
            lux < 10 -> {
                layoutRaiz.setBackgroundColor(Color.BLACK)
                tvLuz.setTextColor(Color.WHITE)
            }
            lux < 100 -> {
                layoutRaiz.setBackgroundColor(Color.GRAY)
                tvLuz.setTextColor(Color.WHITE)
            }
            else -> {
                layoutRaiz.setBackgroundColor(Color.YELLOW)
                tvLuz.setTextColor(Color.BLACK)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

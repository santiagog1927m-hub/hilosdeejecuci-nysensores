// Ejercicio 27: Detectar si el dispositivo está Boca arriba, Boca abajo o En posición vertical
package com.example.hilosejecucinsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class Ejercicio27Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var tvPosicion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio27)

        tvPosicion = findViewById(R.id.tvPosicion)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        val y = event.values[1]
        val z = event.values[2]

        when {
            z > 8.0f -> tvPosicion.text = "Posición: Boca arriba"
            z < -8.0f -> tvPosicion.text = "Posición: Boca abajo"
            abs(y) > 8.0f -> tvPosicion.text = "Posición: En posición vertical"
            else -> tvPosicion.text = "Posición: Inclinado"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

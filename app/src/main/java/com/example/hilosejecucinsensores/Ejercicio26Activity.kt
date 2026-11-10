// Ejercicio 26: Detectar cuándo el usuario sacude el teléfono (Shake detector)
package com.example.hilosejecucinsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class Ejercicio26Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var tvAgitado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio26)

        tvAgitado = findViewById(R.id.tvAgitado)
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
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        // Calcular la magnitud del vector de aceleración
        val aceleracion = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

        // Si supera el umbral de agitación (ej: 15 m/s²)
        if (aceleracion > 15f) {
            tvAgitado.text = "¡Agitado! (${aceleracion.toInt()} m/s²)"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

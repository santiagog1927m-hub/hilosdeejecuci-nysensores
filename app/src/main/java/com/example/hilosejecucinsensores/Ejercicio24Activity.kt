// Ejercicio 24: Usar Sensor.TYPE_STEP_COUNTER o TYPE_STEP_DETECTOR para contar los pasos
package com.example.hilosejecucinsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio24Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private var contadorPasos = 0
    private lateinit var tvPasos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio24)

        tvPasos = findViewById(R.id.tvPasos)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Intentar usar STEP_DETECTOR o STEP_COUNTER
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
            ?: sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    override fun onResume() {
        super.onResume()
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        contadorPasos++
        tvPasos.text = "Pasos: $contadorPasos"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

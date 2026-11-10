// Ejercicio 19: Cambiar el color de fondo según sensor de proximidad (Cerca -> Rojo, Lejos -> Verde)
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

class Ejercicio19Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    private lateinit var layoutRaiz: View
    private lateinit var tvProximidad: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio19)

        layoutRaiz = findViewById(R.id.layoutRaiz)
        tvProximidad = findViewById(R.id.tvProximidad)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        val distancia = event.values[0]
        val maxRango = proximitySensor?.maximumRange ?: 5f

        if (distancia < maxRango) {
            layoutRaiz.setBackgroundColor(Color.RED)
            tvProximidad.text = "Cerca ($distancia cm)"
        } else {
            layoutRaiz.setBackgroundColor(Color.GREEN)
            tvProximidad.text = "Lejos ($distancia cm)"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

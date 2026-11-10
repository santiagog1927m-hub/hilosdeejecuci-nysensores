// Ejercicio 25: Fondo de pantalla según proximidad y luz (Oscuro y cerca -> Negro, Claro y lejos -> Blanco)
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

class Ejercicio25Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    private var lightSensor: Sensor? = null

    private var esCerca = false
    private var esOscuro = false

    private lateinit var layoutRaiz: View
    private lateinit var tvEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio25)

        layoutRaiz = findViewById(R.id.layoutRaiz)
        tvEstado = findViewById(R.id.tvEstado)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        lightSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            val maxRange = proximitySensor?.maximumRange ?: 5f
            esCerca = event.values[0] < maxRange
        }

        if (event.sensor.type == Sensor.TYPE_LIGHT) {
            esOscuro = event.values[0] < 20f // Menos de 20 lux se considera oscuro
        }

        actualizarFondo()
    }

    private fun actualizarFondo() {
        if (esOscuro && esCerca) {
            layoutRaiz.setBackgroundColor(Color.BLACK)
            tvEstado.setTextColor(Color.WHITE)
            tvEstado.text = "Oscuro y Cerca -> Fondo NEGRO"
        } else if (!esOscuro && !esCerca) {
            layoutRaiz.setBackgroundColor(Color.WHITE)
            tvEstado.setTextColor(Color.BLACK)
            tvEstado.text = "Claro y Lejos -> Fondo BLANCO"
        } else {
            layoutRaiz.setBackgroundColor(Color.GRAY)
            tvEstado.setTextColor(Color.WHITE)
            tvEstado.text = "Estado Intermedio -> Fondo GRIS"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

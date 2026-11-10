// Ejercicio 28: Registrar número de pasos, nivel de movimiento y orientación promedio en un RecyclerView
package com.example.hilosejecucinsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt

class Ejercicio28Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var pasoCount = 0
    private var ultimoMovimiento = "Bajo"
    private var ultimaOrientacion = "Horizontal"

    private val listaRegistros = mutableListOf<RegistroData>()
    private lateinit var adapter: RegistroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio28)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val rvDatos = findViewById<RecyclerView>(R.id.rvDatos)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        adapter = RegistroAdapter(listaRegistros)
        rvDatos.layoutManager = LinearLayoutManager(this)
        rvDatos.adapter = adapter

        btnRegistrar.setOnClickListener {
            pasoCount += (1..5).random()
            listaRegistros.add(0, RegistroData(pasoCount, ultimoMovimiento, ultimaOrientacion))
            adapter.notifyItemInserted(0)
            rvDatos.scrollToPosition(0)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val mag = sqrt((event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]).toDouble()).toFloat()
            ultimoMovimiento = when {
                mag > 15f -> "Alto (Agitando)"
                mag > 11f -> "Medio (Caminando)"
                else -> "Bajo (Reposo)"
            }
        }

        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val rot = sqrt((event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]).toDouble()).toFloat()
            ultimaOrientacion = if (rot > 1f) "Girando" else "Estable"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

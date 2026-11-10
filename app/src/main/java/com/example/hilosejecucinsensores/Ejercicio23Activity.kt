// Ejercicio 23: Mover un círculo (ImageView) inclinando el dispositivo con los valores del acelerómetro
package com.example.hilosejecucinsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio23Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var imgCirculo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio23)

        imgCirculo = findViewById(R.id.imgCirculo)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        val ax = event.values[0]
        val ay = event.values[1]

        // Mover la imagen según la inclinación
        val newX = imgCirculo.x - (ax * 5)
        val newY = imgCirculo.y + (ay * 5)

        imgCirculo.x = newX
        imgCirculo.y = newY
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

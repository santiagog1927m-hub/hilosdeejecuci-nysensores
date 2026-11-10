// Ejercicio 6: Simular sensor con datos aleatorios cada 500ms hasta presionar detener
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class Ejercicio6Activity : AppCompatActivity() {

    private var sensorJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        val tvDatos = findViewById<TextView>(R.id.tvDatos)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val btnDetener = findViewById<Button>(R.id.btnDetener)

        btnIniciar.setOnClickListener {
            sensorJob?.cancel() // Cancelar si ya estaba corriendo
            sensorJob = lifecycleScope.launch {
                while (isActive) {
                    val valorSimulado = Random.nextInt(100)
                    tvDatos.text = "Valor Sensor: $valorSimulado"
                    delay(500) // 500 ms
                }
            }
        }

        btnDetener.setOnClickListener {
            sensorJob?.cancel()
            tvDatos.text = "Sensor Detenido"
        }
    }
}

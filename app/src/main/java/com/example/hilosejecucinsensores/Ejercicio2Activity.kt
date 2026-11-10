// Ejercicio 2: Carga progresiva del 0 al 100 en ProgressBar y TextView cada 100ms
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvPorcentaje = findViewById<TextView>(R.id.tvPorcentaje)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            Thread {
                for (i in 0..100) {
                    Thread.sleep(100) // Retardo de 100ms
                    runOnUiThread {
                        progressBar.progress = i
                        tvPorcentaje.text = "$i%"
                    }
                }
            }.start()
        }
    }
}

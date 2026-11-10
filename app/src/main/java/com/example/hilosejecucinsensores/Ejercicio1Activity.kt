// Ejercicio 1: Iniciar hilo que escribe números del 1 al 10 con retardo de 1s
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val tvContador = findViewById<TextView>(R.id.tvContador)

        btnIniciar.setOnClickListener {
            // Se inicia un hilo secundario
            Thread {
                for (i in 1..10) {
                    Thread.sleep(1000) // Retardo de 1 segundo
                    // Actualizar UI desde hilo secundario
                    runOnUiThread {
                        tvContador.text = "$i"
                    }
                }
            }.start()
        }
    }
}

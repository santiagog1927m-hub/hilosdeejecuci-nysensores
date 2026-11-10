// Ejercicio 3: Tres hilos distintos (pares, impares, alfabeto) intercalados
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnIniciar.setOnClickListener {
            tvResultado.text = ""

            // Hilo 1: Números pares
            Thread {
                for (i in 2..10 step 2) {
                    Thread.sleep(300)
                    runOnUiThread { tvResultado.append("Hilo Pares: $i\n") }
                }
            }.start()

            // Hilo 2: Números impares
            Thread {
                for (i in 1..9 step 2) {
                    Thread.sleep(300)
                    runOnUiThread { tvResultado.append("Hilo Impares: $i\n") }
                }
            }.start()

            // Hilo 3: Letras del alfabeto
            Thread {
                for (c in 'A'..'J') {
                    Thread.sleep(300)
                    runOnUiThread { tvResultado.append("Hilo Letras: $c\n") }
                }
            }.start()
        }
    }
}

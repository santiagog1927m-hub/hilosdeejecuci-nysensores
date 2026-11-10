// Ejercicio 5: Botón que lanza corrutina imprimiendo mensajes cada segundo por 5 segundos
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Ejercicio5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)

        btnIniciar.setOnClickListener {
            tvMensaje.text = ""
            // Lanzar corrutina vinculada al ciclo de vida
            lifecycleScope.launch {
                for (i in 1..5) {
                    delay(1000)
                    tvMensaje.append("Mensaje $i (segundo $i)\n")
                }
            }
        }
    }
}

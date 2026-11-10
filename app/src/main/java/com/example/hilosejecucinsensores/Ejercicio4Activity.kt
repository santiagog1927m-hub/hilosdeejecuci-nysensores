// Ejercicio 4: Implementar una clase MiTarea que realiza una operación matemática (suma de primeros N números)
package com.example.hilosejecucinsensores

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Clase MiTarea que implementa Runnable
class MiTarea(private val n: Int, private val alFinalizar: (Int) -> Unit) : Runnable {
    override fun run() {
        var suma = 0
        for (i in 1..n) {
            suma += i
        }
        alFinalizar(suma)
    }
}

class Ejercicio4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        val etNumero = findViewById<EditText>(R.id.etNumero)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val n = etNumero.text.toString().toIntOrNull() ?: 0
            val tarea = MiTarea(n) { suma ->
                runOnUiThread {
                    tvResultado.text = "Suma de 1 a $n: $suma"
                }
            }
            Thread(tarea).start()
        }
    }
}

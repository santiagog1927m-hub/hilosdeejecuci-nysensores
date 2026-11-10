// Menú Principal - Guía de Ejercicios Hilos y Sensores SENA ADSO
package com.example.hilosejecucinsensores

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindButton(R.id.btnEx1, Ejercicio1Activity::class.java)
        bindButton(R.id.btnEx2, Ejercicio2Activity::class.java)
        bindButton(R.id.btnEx3, Ejercicio3Activity::class.java)
        bindButton(R.id.btnEx4, Ejercicio4Activity::class.java)
        bindButton(R.id.btnEx5, Ejercicio5Activity::class.java)
        bindButton(R.id.btnEx6, Ejercicio6Activity::class.java)
        bindButton(R.id.btnEx7, Ejercicio7Activity::class.java)
        bindButton(R.id.btnEx8, Ejercicio8Activity::class.java)
        bindButton(R.id.btnEx9, Ejercicio9Activity::class.java)
        bindButton(R.id.btnEx10, Ejercicio10Activity::class.java)
        bindButton(R.id.btnEx11, Ejercicio11Activity::class.java)
        bindButton(R.id.btnEx12, Ejercicio12Activity::class.java)
        bindButton(R.id.btnEx13, Ejercicio13Activity::class.java)
        bindButton(R.id.btnEx14, Ejercicio14Activity::class.java)
        bindButton(R.id.btnEx15, Ejercicio15Activity::class.java)
        bindButton(R.id.btnEx16, Ejercicio16Activity::class.java)
        bindButton(R.id.btnEx17, Ejercicio17Activity::class.java)
        bindButton(R.id.btnEx18, Ejercicio18Activity::class.java)
        bindButton(R.id.btnEx19, Ejercicio19Activity::class.java)
        bindButton(R.id.btnEx20, Ejercicio20Activity::class.java)
        bindButton(R.id.btnEx21, Ejercicio21Activity::class.java)
        bindButton(R.id.btnEx22, Ejercicio22Activity::class.java)
        bindButton(R.id.btnEx23, Ejercicio23Activity::class.java)
        bindButton(R.id.btnEx24, Ejercicio24Activity::class.java)
        bindButton(R.id.btnEx25, Ejercicio25Activity::class.java)
        bindButton(R.id.btnEx26, Ejercicio26Activity::class.java)
        bindButton(R.id.btnEx27, Ejercicio27Activity::class.java)
        bindButton(R.id.btnEx28, Ejercicio28Activity::class.java)
    }

    private fun bindButton(buttonId: Int, activityClass: Class<*>) {
        findViewById<Button>(buttonId)?.setOnClickListener {
            startActivity(Intent(this, activityClass))
        }
    }
}

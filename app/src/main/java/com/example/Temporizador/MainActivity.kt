package com.example.cronometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.example.cronometro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var tiempo: CountDownTimer
    var contador = 0
    var segundo: Long = 0
    var minuto = 0
    var hora = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contador = 1
        segundo = 1000 * 60
        minuto = 0
        hora = 0
        binding.btniniciar.setOnClickListener() { iniciar() }
        binding.btnreiniciar.setOnClickListener() { reiniciar() }
        binding.btndetener.setOnClickListener() { detener() }
        tiempo = object : CountDownTimer(segundo, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txttiempo.setTextColor(getColor(R.color.black))
                if (contador == 60) {
                    minuto++
                    contador = 0
                    if (minuto == 60) {
                        hora++
                        minuto = 0
                    }
                }
                binding.txttiempo.text = "${hora}:${minuto}:${contador++}"
            }

            override fun onFinish() {

            }
        }
    }

    private fun iniciar() {
        tiempo.start()
    }

    private fun reiniciar() {
        tiempo.cancel()
        contador = 0
        segundo = 0
        minuto = 0
        hora = 0
        binding.txttiempo.text = "${hora}:${minuto}:${contador}"
    }

    private fun detener() {
        tiempo.cancel()
    }
}

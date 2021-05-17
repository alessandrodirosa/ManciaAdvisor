package com.alessandrodirosa.android.manciaadvisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alessandrodirosa.android.manciaadvisor.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calcolaMancia() }
    }

    private fun calcolaMancia(){
        val costo = binding.moneyPaidEdt.text.toString().toDoubleOrNull()
        if (costo == null){
            binding.moneyPaidEdt.error = "Inserisci un valore valido!"
        }else{
            println("$costo")
            val percentuale = when(binding.tipsRadioGroup.checkedRadioButtonId){
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            var risultato = percentuale* costo
            val arrotondare = binding.roundSwitch.isChecked
            if(arrotondare)
                risultato = ceil(risultato)
            val risultatoFormattato = NumberFormat.getCurrencyInstance().format(risultato)
            binding.resultTw.text = getString(R.string.risultato_string,risultatoFormattato)
        }
    }
}
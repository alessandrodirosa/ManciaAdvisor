package com.alessandrodirosa.android.manciaadvisor

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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
        binding.moneyPaidEdt.setOnKeyListener { v, keyCode, _ -> handleKeyEvent(v,keyCode) }
    }

    private fun calcolaMancia(){
        val costo = binding.moneyPaidEdt.text.toString().toDoubleOrNull()
        if (costo == null){
            binding.moneyPaidLayout.error = "Inserisci un valore valido!"
        }else{
            val percentuale = when(binding.tipsRadioGroup.checkedRadioButtonId){
                R.id.option_ten_percent -> 0.10
                R.id.option_eight_percent -> 0.08
                else -> 0.05
            }
            var risultato = percentuale* costo
            val arrotondare = binding.roundSwitch.isChecked
            if(arrotondare)
                risultato = ceil(risultato)
            val risultatoFormattato = NumberFormat.getCurrencyInstance().format(risultato)
            binding.resultTw.text = getString(R.string.risultato_string,risultatoFormattato)
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
package com.example.calculadora_x

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1EditText = findViewById<EditText>(R.id.num1)
        val num2EditText = findViewById<EditText>(R.id.num2)
        val operacionesRadioGroup = findViewById<RadioGroup>(R.id.operacionesRadioGroup)
        val dividirCheckBox = findViewById<CheckBox>(R.id.dividirCheckBox)
        val calcularButton = findViewById<Button>(R.id.calcularButton)
        val resultadoEditText = findViewById<EditText>(R.id.resultadoEditText)

        calcularButton.setOnClickListener {
            try {
                val num1 = num1EditText.text.toString().toDoubleOrNull()
                val num2 = num2EditText.text.toString().toDoubleOrNull()

                if (num1 == null || num2 == null) {
                    resultadoEditText.setText("Error: Ingresa números válidos")
                    return@setOnClickListener
                }

                var resultado: Double? = null

                when (operacionesRadioGroup.checkedRadioButtonId) {
                    R.id.sumarRadioButton -> resultado = num1 + num2
                    R.id.restarRadioButton -> resultado = num1 - num2
                    R.id.multiplicarRadioButton -> resultado = num1 * num2
                    else -> {
                        if (num2 != 0.0) {
                            resultado = num1 / num2
                        } else {
                            resultado = Double.NaN
                        }
                    }
                }

                if (resultado != null && !resultado.isNaN()) {
                    resultadoEditText.setText(resultado.toString())
                } else {
                    resultadoEditText.setText("Error: División por cero")
                }
            } catch (e: Exception) {
                resultadoEditText.setText("Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}

package br.edu.ifsp.scl.ads.prdm.sc3039056.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etDisplay: EditText
    private var operacao: Char? = null
    private var numeroAnterior: Double = 0.0
    private var novoNumero: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDisplay = findViewById(R.id.etDisplay)

        val botoesNumeros = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        for (btn in botoesNumeros) {
            btn.setOnClickListener {
                if (novoNumero) {
                    etDisplay.setText(btn.text)
                    novoNumero = false
                } else {
                    etDisplay.append(btn.text)
                }
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { definirOperacao('+') }
        findViewById<Button>(R.id.btnSub).setOnClickListener { definirOperacao('-') }
        findViewById<Button>(R.id.btnMul).setOnClickListener { definirOperacao('*') }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { definirOperacao('/') }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { calcularResultado() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { limpar() }
    }

    private fun definirOperacao(op: Char) {
        numeroAnterior = etDisplay.text.toString().toDoubleOrNull() ?: 0.0
        operacao = op
        novoNumero = true
    }

    private fun calcularResultado() {
        val numeroAtual = etDisplay.text.toString().toDoubleOrNull() ?: return
        var resultado = 0.0

        when (operacao) {
            '+' -> resultado = numeroAnterior + numeroAtual
            '-' -> resultado = numeroAnterior - numeroAtual
            '*' -> resultado = numeroAnterior * numeroAtual
            '/' -> {
                if (numeroAtual == 0.0) {
                    Toast.makeText(this, "Erro: divisão por zero", Toast.LENGTH_SHORT).show()
                    return
                } else {
                    resultado = numeroAnterior / numeroAtual
                }
            }
        }
        etDisplay.setText(resultado.toString())
        novoNumero = true
    }

    private fun limpar() {
        etDisplay.setText("")
        numeroAnterior = 0.0
        operacao = null
        novoNumero = true
    }
}

package hr.ferit.markobudimir.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvResult: TextView? = null
    private var btnClear : Button? = null
    private var btnDecimalPoint: Button? = null
    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        btnClear = findViewById(R.id.btnClear)

        btnDecimalPoint = findViewById(R.id.btnDot)

        btnClear?.setOnClickListener {
            tvResult?.text = ""
            lastNumeric = false
            lastDot = false
        }

        btnDecimalPoint?.setOnClickListener {
            if(lastNumeric && !lastDot){
                tvResult?.append(".")
                lastNumeric = false
                lastDot = true
            }
        }
    }

    fun onDigit(view: View){
        tvResult?.append((view as Button).text)
        lastNumeric = true
    }

    fun onOperator(view: View) {
        tvResult?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvResult?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value : String): Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

}
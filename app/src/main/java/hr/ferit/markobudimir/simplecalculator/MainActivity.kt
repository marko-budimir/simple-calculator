package hr.ferit.markobudimir.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvResult: TextView? = null
    private var btnClear : Button? = null
    private var btnEqual : Button? = null
    private var btnDecimalPoint: Button? = null
    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        btnClear = findViewById(R.id.btnClear)
        btnEqual = findViewById(R.id.btnEqual)
        btnDecimalPoint = findViewById(R.id.btnDot)

        btnClear?.setOnClickListener {
            tvResult?.text = ""
            lastNumeric = false
            lastDot = false
        }

        btnEqual?.setOnClickListener {
            if(lastNumeric){
                var tvValue = tvResult?.text.toString()
                var prefix = ""
                try {
                    if(tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                    if(tvValue.contains("-")){
                        val splitValue = tvValue.split("-")
                        var firstNumber: String = prefix + splitValue[0]
                        var secondNumber: String = splitValue[1]
                        tvResult?.text = removeZeroAfterDot((firstNumber.toDouble() - secondNumber.toDouble()).toString())
                    }else if(tvValue.contains("+")){
                        val splitValue = tvValue.split("+")
                        var firstNumber: String = prefix + splitValue[0]
                        var secondNumber: String = splitValue[1]
                        tvResult?.text = removeZeroAfterDot((firstNumber.toDouble() + secondNumber.toDouble()).toString())
                    }else if(tvValue.contains("*")){
                        val splitValue = tvValue.split("*")
                        var firstNumber: String = prefix + splitValue[0]
                        var secondNumber: String = splitValue[1]
                        tvResult?.text = removeZeroAfterDot((firstNumber.toDouble() * secondNumber.toDouble()).toString())
                    }else if(tvValue.contains("/")){
                        val splitValue = tvValue.split("/")
                        var firstNumber: String = prefix + splitValue[0]
                        var secondNumber: String = splitValue[1]
                        tvResult?.text = removeZeroAfterDot((firstNumber.toDouble() / secondNumber.toDouble()).toString())
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }
        }

        btnDecimalPoint?.setOnClickListener {
            if(lastNumeric && !lastDot){
                tvResult?.append(".")
                lastNumeric = false
                lastDot = true
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.endsWith(".0"))
            value = result.substring(0, result.length - 2)

        return value
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
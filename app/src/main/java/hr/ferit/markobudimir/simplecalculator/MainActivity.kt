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
    private var btnAdd : Button? = null
    private var btnSubtract : Button? = null
    private var btnMultiply: Button? = null
    private var btnDivide: Button? = null
    private var btnEqual: Button? = null
    private var btnDecimalPoint: Button? = null
    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        btnClear = findViewById(R.id.btnClear)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnEqual = findViewById(R.id.btnEqual)
        btnDecimalPoint = findViewById(R.id.btnDot)

        btnClear?.setOnClickListener {
            tvResult?.text = ""
            lastNumeric = false
            lastDot = false
        }

        btnAdd?.setOnClickListener {

        }

        btnSubtract?.setOnClickListener {

        }

        btnMultiply?.setOnClickListener {

        }

        btnDivide?.setOnClickListener {

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




}
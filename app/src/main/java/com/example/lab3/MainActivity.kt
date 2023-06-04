package com.example.lab3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClick(view: View?) {
        val textShow = findViewById<TextView>(R.id.textView)
        when(view?.id){
            R.id.button0->{
                if (textShow.text.toString() != "0" || textShow.text.takeLast(1).toString() == "."){
                    click("0")
                }
            }
            R.id.button1->{ click("1") }
            R.id.button2->{ click("2") }
            R.id.button3->{ click("3") }
            R.id.button4->{ click("4") }
            R.id.button5->{ click("5") }
            R.id.button6->{ click("6") }
            R.id.button7->{ click("7") }
            R.id.button8->{ click("8") }
            R.id.button9->{ click("9") }
            R.id.buttonR->{ click(".") }
            R.id.buttonAC->{
                textShow.text = "0"
            }
            R.id.buttonPM->{
                if ("-" in textShow.text.toString()){
                    textShow.text = textShow.text.toString().replace("-", "")
                }
                else{
                    textShow.text = "-" + textShow.text.toString()
                }
            }
            R.id.buttonProcent->{
                if (textShow.text != "0" && textShow.text != "Ошибка" &&
                    "+" !in textShow.text.toString() && "*" !in textShow.text.toString()
                    && "/" !in textShow.text.toString()){
                    textShow.text = (textShow.text.toString().toDouble() / 100).toString();
                }
            }
            R.id.buttonDelenie->{
                onOperatorButtonClicked("/")
            }
            R.id.buttonProizv->{
                onOperatorButtonClicked("*")
            }
            R.id.buttonPlus->{
                onOperatorButtonClicked("+")
            }
            R.id.buttonMin->{
                onOperatorButtonClicked("-")
            }
            R.id.buttonRavno->{
                val i = textShow.text.takeLast(1).toString()
                if (!isInteger(i)){
                    textShow.text = textShow.text.toString().dropLast(1)
                }

                try {
                    val ex = ExpressionBuilder(textShow.text.toString()).build()
                    val res = ex.evaluate()
                    val longRes = res.toLong()

                    if (res == longRes.toDouble()) {
                        textShow.text = longRes.toString()
                    }
                    else{
                        textShow.text = res.toString()
                    }
                } catch (e: Exception) {
                    textShow.text = "Ошибка"
                }
            }
        }
    }

    private fun click(string: String){
        val textShow = findViewById<TextView>(R.id.textView)
        if (textShow.text.toString() == "0" || textShow.text == "Ошибка"){
            textShow.text = string
        }
        else{
            textShow.text = addToInputText(string)
        }
    }

    private fun onOperatorButtonClicked(operator: String) {
        val textShow = findViewById<TextView>(R.id.textView)
        val i = textShow.text.takeLast(1).toString()

        if (!isInteger(i) && i != "a") {
            textShow.text = textShow.text.toString().dropLast(1) + operator
        }
        else{
            textShow.text = addToInputText(operator)
        }
    }
    private fun addToInputText(buttonValue: String): String {
        val textShow = findViewById<TextView>(R.id.textView)
        return "${textShow.text}$buttonValue"
    }

    private val integerChars = '0'..'9'
    private fun isInteger(input: String) = input.all { it in integerChars }
}
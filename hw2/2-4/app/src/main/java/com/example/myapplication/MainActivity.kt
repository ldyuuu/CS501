package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.EditText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.view.Gravity
import android.widget.GridLayout
import android.widget.LinearLayout
import kotlin.math.sqrt
import android.widget.Button
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private lateinit var display: EditText
    private val calculator = Calculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 50, 50, 50)
        }

        display = EditText(this).apply {
            gravity = Gravity.END
            textSize = 32f
            isFocusable = true
            isFocusableInTouchMode = true
            hint = "0"
        }
        mainLayout.addView(display)

        val buttonLayout = GridLayout(this).apply {
            rowCount = 5
            columnCount = 5
        }

        val buttons = listOf(
            "1" to "1", "2" to "2", "3" to "3", "+" to "+","*" to "*",
            "4" to "4", "5" to "5", "6" to "6", "-" to "-","/" to "/",
            "7" to "7", "8" to "8", "9" to "9", "sqrt" to "sqrt",
            "0" to "0", "." to ".", "C" to "clear", "=" to "="
        )

        var currentRow = 0
        var currentColumn = 0

        buttons.forEach { (text, action) ->
            val button = Button(this).apply {
                this.text = text
                setOnClickListener { onButtonClick(action) }

                val layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(currentRow)
                    columnSpec = GridLayout.spec(currentColumn, if (text == "0" || text == "sqrt") 2 else 1)
                    width = if (text=="sqrt"|| text=="0") 400 else 200
                    height = 200
                }
                this.layoutParams = layoutParams
            }
            buttonLayout.addView(button)
            currentColumn += if (text == "sqrt" || text == "0") 2 else 1
            if (currentColumn >= 5) {
                currentColumn = 0
                currentRow++
            }
        }
        mainLayout.addView(buttonLayout)
        setContentView(mainLayout)
    }


    private fun onButtonClick(action: String) {
        when (action) {
            "clear" -> clearDisplay()
            "=" -> calculateResult()
            "+" -> setOperator1("+")
            "-" -> setOperator1("-")
            "*" -> setOperator1("*")
            "/" -> setOperator1("/")
            "sqrt" -> calculateSqrt()
            else -> display.append(action)
        }
    }

    private fun setOperator1(op: String) {
        val firstOperand = display.text.toString().toDoubleOrNull()

        if (firstOperand == null) {
            Toast.makeText(this, "Invalid Operand 1", Toast.LENGTH_SHORT).show()

        } else {
            calculator.firstOperand = firstOperand
            calculator.setOperator2(op)
            display.text.clear()
        }
    }

    private fun calculateSqrt() {
        val value = display.text.toString().toDoubleOrNull()
        val result = calculator.calculateSqrt(value)
        if (result != null) {
            display.setText(result.toString())
        } else {
            Toast.makeText(this, "Invalid input for sqrt", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearDisplay() {
        display.text.clear()
        calculator.clear()
    }

    private fun calculateResult() {
        val secondOperand = display.text.toString().toDoubleOrNull()
        if (secondOperand == null) {
            Toast.makeText(this, "Invalid Operand 2", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val result = calculator.calculate(secondOperand)
            if (result != null) {
                display.setText(result.toString())
            }
        } catch (e: ArithmeticException) {
            Toast.makeText(this, "Error: Division by zero", Toast.LENGTH_SHORT).show()
        }
        calculator.clear()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

class Calculator {
    var firstOperand: Double? = null
    var operator: String? = null

    fun setOperator2(op: String) {
        operator = op
    }

    fun clear() {
        firstOperand = null
        operator = null
    }

    fun calculate(secondOperand: Double?): Double? {
        if (firstOperand == null || secondOperand == null || operator == null) {
            return null
        }

        return try {
            when (operator) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "*" -> firstOperand!! * secondOperand
                "/" -> {
                    if (secondOperand == 0.0) {
                        throw ArithmeticException("Division by zero")
                    } else {
                        firstOperand!! / secondOperand
                    }
                }
                else -> null
            }
        } catch (e: ArithmeticException) {
           throw ArithmeticException("Division by zero")
        }
    }
    fun calculateSqrt(value: Double?): Double? {
        return if (value != null && value >= 0) {
            sqrt(value)
        } else {
            null
        }
    }
}
//Author: HazimShakri
//Labtest: Native Programming
//DateL 4 January 2024

package com.example.mindsharpener1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Random

class Task2 : AppCompatActivity() {
    // UI components
    private var textView4: TextView? = null
    private var textView5: TextView? = null
    private var textView6: TextView? = null
    private var textView8: TextView? = null
    private var editTextText: EditText? = null
    private var button: Button? = null
    private var radioGroup: RadioGroup? = null

    // Game state
    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_2)
        setSupportActionBar(findViewById<Toolbar>(R.id.my_toolbar))

        // Initialize views
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        textView6 = findViewById(R.id.textView6)
        textView8 = findViewById<TextView>(R.id.textView8)
        editTextText = findViewById<EditText>(R.id.editTextText)
        button = findViewById<Button>(R.id.button)
        radioGroup = findViewById(R.id.radioGroup)

        // Set listeners
        button?.setOnClickListener(View.OnClickListener { checkAnswer() })

        radioGroup?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, checkedId -> updateQuestion() })

        // Initial question
        updateQuestion()
    }

    private fun updateQuestion() {
        val selectedLevelId = radioGroup!!.checkedRadioButtonId
        val selectedLevelRadioButton = findViewById<RadioButton>(selectedLevelId)

        val maxDigits: Int
        if (selectedLevelRadioButton != null) {
            val selectedLevel = selectedLevelRadioButton.text.toString()
            maxDigits = when (selectedLevel) {
                "i3" -> 1
                "i5" -> 2
                "i7" -> 3
                else -> 1
            }

            // Generate random numbers and operator
            val random = Random()
            val firstNumber = random.nextInt(Math.pow(10.0, maxDigits.toDouble()).toInt())
            val secondNumber = random.nextInt(Math.pow(10.0, maxDigits.toDouble()).toInt())
            val operator = random.nextInt(4)

            // Update UI
            textView4!!.text = firstNumber.toString()
            textView6!!.text = secondNumber.toString()
            when (operator) {
                0 -> textView5!!.text = "+"
                1 -> textView5!!.text = "-"
                2 -> textView5!!.text = "*"
                3 -> textView5!!.text = "/"
            }

            // Clear user input
            editTextText!!.text.clear()
        }
    }

    private fun checkAnswer() {
        val firstNumber = textView4!!.text.toString().toInt()
        val secondNumber = textView6!!.text.toString().toInt()
        val operator = getOperator(textView5!!.text.toString())
        val correctAnswer = calculateAnswer(firstNumber, secondNumber, operator)

        val userAnswerString = editTextText!!.text.toString()

        if (!userAnswerString.isEmpty()) {
            val userAnswer = userAnswerString.toInt()
            if (userAnswer == correctAnswer) {
                points++
            } else {
                points--
            }
        }

        textView8!!.text = points.toString()

        // Next question
        updateQuestion()
    }

    private fun getOperator(operatorString: String): Int {
        return when (operatorString) {
            "+" -> 0
            "-" -> 1
            "*" -> 2
            "/" -> 3
            else -> 0
        }
    }

    private fun calculateAnswer(firstNumber: Int, secondNumber: Int, operator: Int): Int {
        return when (operator) {
            0 -> firstNumber + secondNumber
            1 -> firstNumber - secondNumber
            2 -> firstNumber * secondNumber
            3 ->
                if (secondNumber != 0) firstNumber / secondNumber else 0
            else -> 0
        }
    }
}
package com.example.bmiapp

import android.app.ActionBar
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var toolBar: Toolbar
    lateinit var feetEditText: EditText
    lateinit var inchEditText: EditText
    lateinit var massEditText: EditText
    lateinit var showBmiBtn: Button
    lateinit var bmiResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolBar)
        showToolBar(this, toolBar)

        feetEditText = findViewById(R.id.feetEditText)
        inchEditText = findViewById(R.id.inchEditText)
        massEditText = findViewById(R.id.massEditText)
        showBmiBtn = findViewById(R.id.showBmiBtn)
        bmiResultTextView = findViewById(R.id.bmiResultTextView)


        showBmiBtn.setOnClickListener {
            val feet = feetEditText.text.toString().toInt()
            val inch = inchEditText.text.toString().toInt()
            val mass = massEditText.text.toString().toInt()
            /*
                calculate the BMI getting the result from calculateBMI function
             */
            val bmiResult = calculateBMI(feet, inch, mass)

            if (bmiResult == "Overweight") {
                val bg = bmiResultTextView.background as GradientDrawable
                bg.setColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_error))
                bmiResultTextView.text = "Overweight"

            } else if (bmiResult == "Underweight") {
                val bg = bmiResultTextView.background as GradientDrawable
                bg.setColor(ContextCompat.getColor(this, android.R.color.holo_orange_light))
                bmiResultTextView.text = "Underweight"

            } else {
                val bg = bmiResultTextView.background as GradientDrawable
                bg.setColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
                bmiResultTextView.text = "Healthy"
            }
        }
    }
}

 /*
    Toolbar function
 */
fun showToolBar(activity: AppCompatActivity, toolbar: Toolbar) {
    activity.setSupportActionBar(toolbar)

    activity.supportActionBar?.setDisplayShowTitleEnabled(false)

    val title = TextView(activity).apply{
        text = "BMI Calculator"
        textSize = 24f
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
        gravity = Gravity.CENTER
    }

    val layoutPar = Toolbar.LayoutParams(
        Toolbar.LayoutParams.WRAP_CONTENT,
        Toolbar.LayoutParams.WRAP_CONTENT,
        Gravity.CENTER
    )

    toolbar.addView(title, layoutPar)
}

 /*
    Calculate BMI function
 */
fun calculateBMI(feet: Int, inch: Int, mass: Int): String {
    val totalInch = (feet * 12) + inch
    val totalCm = totalInch * 2.53
    val totalM = totalCm / 100

    val bmi = mass/ (totalM * totalM)


    if (bmi > 25) {
        return "Overweight"
    } else if (bmi < 18) {
        return "Underweight"
    } else {
        return "Healthy"
    }
}
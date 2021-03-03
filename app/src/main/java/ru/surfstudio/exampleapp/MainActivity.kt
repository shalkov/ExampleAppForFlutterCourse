package ru.surfstudio.exampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textEt: EditText
    private lateinit var screenBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        screenBtn.setOnClickListener {
            openSecondActivity(textEt.text.toString())
        }

    }

    private fun initViews() {
        textEt = findViewById(R.id.text_et)
        screenBtn = findViewById(R.id.open_second_activity_btn)
    }

    private fun openSecondActivity(text: String) {
        val secondActivityIntent = Intent(this, SecondActivity::class.java)
        secondActivityIntent.putExtra(Consts.TEXT_ARG, text)
        startActivity(secondActivityIntent)
    }
}
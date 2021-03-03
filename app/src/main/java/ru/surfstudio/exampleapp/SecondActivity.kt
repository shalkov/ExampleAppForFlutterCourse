package ru.surfstudio.exampleapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var titleTv: TextView
    private lateinit var mapBtn: Button
    private lateinit var emailBtn: Button

    private var titleText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initParams()
        initViews()

        titleTv.text = titleText
        mapBtn.setOnClickListener {
            openMap()
        }

        emailBtn.setOnClickListener {
            openEmail()
        }

    }

    private fun initViews() {
        titleTv = findViewById(R.id.second_title_tv)
        mapBtn = findViewById(R.id.second_map_btn)
        emailBtn = findViewById(R.id.second_email_btn)
    }

    private fun initParams() {
        titleText = intent.getStringExtra(Consts.TEXT_ARG)
    }

    private fun openMap() {
        val lat = "55.755826"
        val long = "37.6173"
        val gmmIntentUri = Uri.parse(java.lang.String.format(Consts.NAVIGATION, lat, long))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        val mapChooserIntent = Intent.createChooser(mapIntent, getString(R.string.choose_app_text))
        startActivity(mapChooserIntent)
    }

    private fun openEmail(emailTo: String = "shalkov@surfstudio.ru") {
        val mailTo = "mailto:$emailTo"
        val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
        emailSelectorIntent.data = Uri.parse(mailTo)
        val body = "Пример сообщения!"
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailTo))
            putExtra(Intent.EXTRA_SUBJECT, titleText)
            putExtra(Intent.EXTRA_TEXT, body)
            selector = emailSelectorIntent
            putExtra(Intent.EXTRA_SUBJECT, titleText)
        }

        val emailChooserIntent = Intent.createChooser(emailIntent, title)
        startActivity(emailChooserIntent)
    }
}
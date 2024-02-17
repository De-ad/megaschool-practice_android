package ru.sample.duckapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val duckManager = DuckManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val duckImageView = findViewById<ImageView>(R.id.duckImage)

        duckManager.getStartDuck(duckImageView)

        findViewById<Button>(R.id.sendButton)
            .setOnClickListener {
                val inputField = findViewById<EditText>(R.id.httpCodeInput)
                val input = inputField.text.toString()
                duckManager.getDuck(input,inputField, duckImageView)
            }
    }

}
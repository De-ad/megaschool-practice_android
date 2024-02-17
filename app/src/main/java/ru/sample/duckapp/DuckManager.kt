package ru.sample.duckapp

import android.widget.EditText
import android.widget.ImageView

class DuckManager {
    private val apiCallHandler = ApiCallHandler()
    fun getStartDuck(duckImageView: ImageView){
        apiCallHandler.getRandomDuck(duckImageView)
    }

    fun getDuck(str: String, inputField: EditText, duckImageView: ImageView){
        val numValue = str.toIntOrNull()

        when {
            str.isEmpty() -> apiCallHandler.getRandomDuck(duckImageView)
            numValue == null -> inputField.error = "Incorrect input!"
            numValue !in 100..599 -> inputField.error = "HTTP response code should be between 100 and 599!"
            else -> apiCallHandler.getHttpCodeDuck(str, inputField, duckImageView)
        }
    }
}
package ru.sample.duckapp

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sample.duckapp.domain.Duck
import ru.sample.duckapp.infra.Api

class ApiCallHandler {
    fun getHttpCodeDuck(input: String, inputField: EditText, duckImageView: ImageView){
        val call = Api.ducksApi.getHttpCodeDuck(input)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val imageBytes = response.body()?.bytes()

                    if (imageBytes != null) {
                        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        duckImageView.setImageBitmap(bitmap)
                    } else {
                        Log.e("dev", "Image bytes are null")
                    }

                } else {
                    inputField.error = "There's no duck for such code!"
                    Log.e("dev", "Response unsuccessful. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("dev", "Network call failed: ${t.message}", t)
            }
        })
    }

    fun getRandomDuck(duckImageView: ImageView){
        val call = Api.ducksApi.getRandomDuck()

        call.enqueue(object : Callback<Duck> {
            override fun onResponse(call: Call<Duck>, response: Response<Duck>) {
                if (response.isSuccessful) {
                    val duck: Duck? = response.body()
                    Picasso.get().load(duck?.url).into(duckImageView)
                } else {
                    Log.e("dev", "Response unsuccessful. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Duck>, t: Throwable) {
                Log.e("dev", "Network call failed: ${t.message}", t)
            }
        })
    }

}
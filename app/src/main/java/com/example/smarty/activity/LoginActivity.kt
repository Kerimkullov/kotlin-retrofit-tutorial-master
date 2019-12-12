package com.example.smarty.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smarty.R
import com.example.smarty.api.ApiServices
import com.example.smarty.api.ServiceBuilder
import com.example.smarty.models.DefaultResponse
import com.example.smarty.models.LoginResponse
import com.example.smarty.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        buttonLogin.setOnClickListener {

            val defaultResponse = DefaultResponse()
            defaultResponse.username = editTextEmail.text.toString()
            defaultResponse.password = editTextPassword.text.toString()


            val apiService = ServiceBuilder.buildService(ApiServices::class.java)
            val requestCall = apiService.userLogin(defaultResponse)
            requestCall.enqueue(object: Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(response.isSuccessful){

                        SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                        val intent = Intent(applicationContext, Dashboard::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)


                    }else{
                        Toast.makeText(applicationContext, "wrong credentials", Toast.LENGTH_LONG).show()
                    }

                }
            })

        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}
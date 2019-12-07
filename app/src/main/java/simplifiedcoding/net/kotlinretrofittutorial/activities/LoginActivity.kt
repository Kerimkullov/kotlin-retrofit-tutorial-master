package simplifiedcoding.net.kotlinretrofittutorial.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import simplifiedcoding.net.kotlinretrofittutorial.R
import simplifiedcoding.net.kotlinretrofittutorial.api.ApiServices
import simplifiedcoding.net.kotlinretrofittutorial.api.ServiceBuilder
import simplifiedcoding.net.kotlinretrofittutorial.models.DefaultResponse
import simplifiedcoding.net.kotlinretrofittutorial.models.LoginResponse
import simplifiedcoding.net.kotlinretrofittutorial.storage.SharedPrefManager

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

                                val intent = Intent(applicationContext, ProfileActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                startActivity(intent)


                            }else{
                                Toast.makeText(applicationContext, response.body()?.token, Toast.LENGTH_LONG).show()
                            }

                        }
                    })

        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}
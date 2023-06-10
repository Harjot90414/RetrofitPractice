package com.harjot.retrofitpractice

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harjot.retrofitpractice.databinding.ActivityRetrofitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class RetrofitActivity : AppCompatActivity() {
    lateinit var binding: ActivityRetrofitBinding
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showProgress = false
        sharedPreferences = this.getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        val langauge = sharedPreferences.getString("locale","hi")?:"hi"
        val locale = Locale(langauge)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config,resources.displayMetrics)

        binding.getUsers.setOnClickListener {
            var apiData : Call<UsersResponseItem> = RetrofitClass()
                .getRetrofitClass().getUsers(506)

            apiData.enqueue(object : Callback<UsersResponseItem> {
                override fun onResponse(call: Call<UsersResponseItem>, response: Response<UsersResponseItem>) {
                    System.out.println("in response body ${response.body()}")
                    if(response.code() == 200){
                        var userResponse = response.body() as UsersResponseItem
                        System.out.println("in response ${response} ${userResponse.email}")
                    }
                }

                override fun onFailure(call: Call<UsersResponseItem>, t: Throwable) {
                    System.out.println(" in failure ${t.message}")
                }
            })
        }

        binding.addCustomer.setOnClickListener {
            binding.showProgress = true
            var hashmap = HashMap<String, Any>()
            hashmap.put("email","Harjot@gmail.com")
            hashmap.put("name", "Harjot")
            hashmap.put("gender", "Male")
            hashmap.put("password", "1111")
            hashmap.put("contact","6283405923")

            var apiData : Call<UsersResponseItem> = RetrofitClass()
                .getRetrofitClass().addCustomer(hashmap)

            apiData.enqueue(object : Callback<UsersResponseItem> {
                override fun onResponse(call: Call<UsersResponseItem>, response: Response<UsersResponseItem>) {
                    System.out.println("in response body ${response.body()}")
                    binding.showProgress = false
                    if(response.code() == 200){
                        //  if(response.body() != null) {
                        var userResponse = response.body() as UsersResponseItem
                        System.out.println("in response ${response} ${userResponse.email}")
                    }
                }

                override fun onFailure(call: Call<UsersResponseItem>, t: Throwable) {
                    binding.showProgress = false
                    System.out.println(" in failure ${t.message}")
                }
            })
        }
    }
}
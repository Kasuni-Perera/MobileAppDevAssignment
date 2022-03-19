package com.mobileapp.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.mobileapp.dev.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var API = rf.create(RetrofitInterface::class.java)
        var call = API.post
        call?.enqueue(object:Callback<List<Post?>?>{
            override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
                var postList: List<Post>? = response.body() as List<Post>
                var post = arrayOfNulls<String>(postList!!.size)
                for (i in postList!!.indices) {
                    post[i] = postList!![i]!!.title
                }

                var adapter = ArrayAdapter<String>(
                    applicationContext,
                    android.R.layout.simple_dropdown_item_1line,
                    post
                )
                var listView = findViewById<ListView>(R.id.listview)
                listView.adapter = adapter

            }

            override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
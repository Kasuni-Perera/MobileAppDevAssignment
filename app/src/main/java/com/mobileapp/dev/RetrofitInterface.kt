package com.mobileapp.dev

import com.mobileapp.dev.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @get:GET("posts")
    val post : Call<List<Post?>?>?

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
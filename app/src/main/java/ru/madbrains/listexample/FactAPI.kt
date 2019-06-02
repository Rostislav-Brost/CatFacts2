package ru.madbrains.listexample
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface FactAPI {

    @GET("/bins/w4bj6")
    fun getFacts(): Call<ResponseBody>
}
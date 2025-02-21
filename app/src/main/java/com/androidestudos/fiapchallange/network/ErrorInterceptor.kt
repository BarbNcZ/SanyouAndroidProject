package com.androidestudos.fiapchallange.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        return try {
            val request = chain.request()
            val response = chain.proceed(request)
            if (!response.isSuccessful){
                throw HttpException(
                    retrofit2.Response.error<Any>(
                        response.code(),
                        response.body() ?: ResponseBody.create(null, "Erro Desconhecido")
                    )
                )
            }
            response
        } catch (e: IOException){
        Log.e(this::class.java.simpleName, "Erro na rede ${e.message ?: "Erro desconhecido"}")
            Response.Builder().body(ResponseBody.create(null, "-")).code(500).build()
        } catch (e: HttpException){
            Log.e(this::class.java.simpleName, e.response()?.message() ?: e.message ?: "Erro desconhecido")
            Response.Builder().body(ResponseBody.create(null, "-")).code(500).build()
        } catch (e: Exception){
            Log.e(this::class.java.simpleName, e.message?: "Erro desconhecido")
            Response.Builder().body(ResponseBody.create(null, "-")).code(500).build()
        }
    }
}
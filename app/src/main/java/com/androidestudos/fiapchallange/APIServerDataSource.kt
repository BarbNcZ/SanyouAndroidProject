package com.androidestudos.fiapchallange

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServerDataSource {
    suspend fun createTarefa(
        cdTipoTarefa: Int,
        dsTarefas: String
    ): CreateTarefaResult? {

        return kotlin.runCatching{
            val result = buildAPI().createTarefa(cdTipoTarefa, dsTarefas)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao criar tarefa ${e.message}")
            null
        }

    }

    private fun buildAPI(): APIServer {
        val client = OkHttpClient.Builder()
            .addInterceptor(ErrorInterceptor())
            .build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:8000")
            .build()
            .create(APIServer::class.java)
    }

}
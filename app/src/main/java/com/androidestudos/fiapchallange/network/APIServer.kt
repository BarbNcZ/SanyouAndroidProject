package com.androidestudos.fiapchallange.network

import com.androidestudos.fiapchallange.data.CreateTarefaResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface APIServer {
    @GET("/tarefa/{cd_tipo_tarefa}/{ds_tarefas}")
    suspend fun createTarefa(@Path("cd_tipo_tarefa") cdTipoTarefa: Int, @Path("ds_tarefas") dsTarefas: String): Response<CreateTarefaResult>
}
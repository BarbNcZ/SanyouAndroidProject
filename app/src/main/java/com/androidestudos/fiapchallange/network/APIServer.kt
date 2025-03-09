package com.androidestudos.fiapchallange.network

import android.os.UserHandle
import com.androidestudos.fiapchallange.data.CreateFuncionarioResult
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetCargoResultList
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResultList
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.data.GetTarefaResultList
import com.androidestudos.fiapchallange.data.GetTarefasResultList
import com.androidestudos.fiapchallange.data.GetTipoTarefaResultList
import com.androidestudos.fiapchallange.data.LoginResult
import com.androidestudos.fiapchallange.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface APIServer {

    @PUT("/createtarefa/{cd_tipo_tarefa}/{ds_tarefas}/{cd_funcionario}")
    suspend fun createTarefa(
        @Path("cd_tipo_tarefa") cdTipoTarefa: Int,
        @Path("ds_tarefas") dsTarefas: String,
        @Path("cd_funcionario") cdFuncioanrio: Int
    ): Response<CreateTarefaResult>

    @GET("/tipotarefa")
    suspend fun getTipoTarefa(): Response<GetTipoTarefaResultList>

    @DELETE("/deletetarefa/{cd_tarefas}")
    suspend fun deleteTarefa(@Path("cd_tarefas") cdTarefa: Int): Response<DeleteTarefasResult>

    @GET("/tarefas")
    suspend fun getTarefas(): Response<GetTarefasResultList>

    @GET("/departamentos")
    suspend fun getDepartamentos(): Response<GetDepartamentoResultList>

    @GET("/cargo")
    suspend fun getCargo(): Response<GetCargoResultList>

    @GET("/funcionarios")
    suspend fun getFuncionario(): Response<GetFuncionarioResultList>

    @PUT("/createfuncionario/{cd_depto}/{cd_cargo}/{ds_email}/{nm_funcionario}")
    suspend fun createFuncionario(
        @Path("cd_depto") cdDepartamento: Int,
        @Path("cd_cargo") cdCargo: Int,
        @Path("ds_email") dsEmail: String,
        @Path("nm_funcionario") nmFuncionario: String
    ): Response<CreateFuncionarioResult>

    @GET("/tarefa/{cd_tarefas}")
    suspend fun getTarefa(
        @Path("cd_tarefas") cdTarefas: Int
    ): Response<GetTarefaResult>

    @POST("/login/")
    suspend fun login(
        @Body user: User
    ): Response<LoginResult>

    @GET("/tarefasbyfuncionario/{cd_funcionario}")
    suspend fun getTarefasByFuncionario(
        @Path("cd_funcionario") cdFuncionario: Int
    ): Response<GetTarefaResultList>

}
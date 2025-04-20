package com.androidestudos.fiapchallange.network

import android.util.Log
import com.androidestudos.fiapchallange.data.ConcluirTarefasResult
import com.androidestudos.fiapchallange.data.CreateFuncionarioResult
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.data.DeleteFuncionarioResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult
import com.androidestudos.fiapchallange.data.GetCargoResultList
import com.androidestudos.fiapchallange.data.GetDepartamentoResultList
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.data.GetTarefaResultList
import com.androidestudos.fiapchallange.data.GetTarefasResultList
import com.androidestudos.fiapchallange.data.GetTipoTarefaResultList
import com.androidestudos.fiapchallange.data.LoginResult
import com.androidestudos.fiapchallange.data.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServerDataSource(private val api: APIServer) {
    suspend fun createTarefa(
        cdTipoTarefa: Int,
        cdFuncionario: Int,
        dsTarefas: String,
        estimation: Int,
        time: Long
    ): CreateTarefaResult? {

        return kotlin.runCatching{
            val result = api.createTarefa(cdTipoTarefa, dsTarefas, cdFuncionario, estimation, time)
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


    suspend fun getTipoTarefa(): GetTipoTarefaResultList? {

        return kotlin.runCatching{
            val result = api.getTipoTarefa()
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar tipos de tarefa ${e.message}")
            null
        }

    }

    suspend fun getTarefas(): GetTarefasResultList? {

        return kotlin.runCatching{
            val result = api.getTarefas()
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar tarefas ${e.message}")
            null
        }

    }

    suspend fun getTarefaByFuncionario(cdFuncionario: Int): GetTarefaResultList? {

        return kotlin.runCatching{
            val result = api.getTarefasByFuncionario(cdFuncionario)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar tarefas do funcionario ${e.message}")
            null
        }

    }

    suspend fun getTarefa(cdTarefa: Int): GetTarefaResult? {

        return kotlin.runCatching{
            val result = api.getTarefa(cdTarefa)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar tarefa ${e.message}")
            null
        }

    }

    suspend fun login(user: User): LoginResult? {

        return kotlin.runCatching{
            val result = api.login(user)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao fazer login ${e.message}")
            null
        }

    }

    suspend fun deleteTarefa(
        cdTarefa: Int
    ): DeleteTarefasResult? {

        return kotlin.runCatching{
            val result = api.deleteTarefa(cdTarefa)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao deletar tarefa ${e.message}")
            null
        }

    }

    suspend fun concluirTarefa(
        cdTarefa: Int,
        cdFuncionario: Int
    ): ConcluirTarefasResult? {

        return kotlin.runCatching{
            val result = api.concluirTarefa(cdTarefa, cdFuncionario)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao concluir tarefa ${e.message}")
            null
        }
    }


    suspend fun deleteFuncionario(
        cdFuncionario: Int
    ): DeleteFuncionarioResult? {

        return kotlin.runCatching{
            val result = api.deleteFuncionario(cdFuncionario)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao deletar funcionario ${e.message}")
            null
        }

    }

    suspend fun createFuncionario(
        cdDepto: Int,
        cdCargo: Int,
        dsEmail: String,
        nmFuncionario: String
    ): CreateFuncionarioResult? {

        return kotlin.runCatching{
            val result = api.createFuncionario(cdDepto,cdCargo, dsEmail, nmFuncionario)
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao criar funcionario ${e.message}")
            null
        }

    }

    suspend fun getCargo(): GetCargoResultList? {

        return kotlin.runCatching{
            val result = api.getCargo()
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar cargos ${e.message}")
            null
        }

    }

    suspend fun getFuncionario(): GetFuncionarioResultList? {

        return kotlin.runCatching{
            val result = api.getFuncionario()
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar funcionarios ${e.message}")
            null
        }

    }

    suspend fun getDepartamento(): GetDepartamentoResultList? {

        return kotlin.runCatching {
            val result = api.getDepartamentos()
            if (result.isSuccessful) {
                result.body()
            } else {
                throw Exception("Erro desconhecido")
            }

        }.getOrElse { e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar departamentos ${e.message}")
            null
        }

    }

    suspend fun getRanking(): GetFuncionarioResultList? {

        return kotlin.runCatching{
            val result = api.getRanking()
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar o ranking ${e.message}")
            null
        }

    }


}
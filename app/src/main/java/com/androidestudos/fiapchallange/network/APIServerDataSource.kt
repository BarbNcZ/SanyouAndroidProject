package com.androidestudos.fiapchallange.network

import android.util.Log
import com.androidestudos.fiapchallange.data.CreateFuncionarioResult
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult
import com.androidestudos.fiapchallange.data.GetCargoResultList
import com.androidestudos.fiapchallange.data.GetDepartamentoResultList
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.data.GetTarefasResultList
import com.androidestudos.fiapchallange.data.GetTipoTarefaResultList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServerDataSource(private val api: APIServer) {
    suspend fun createTarefa(
        cdTipoTarefa: Int,
        cdFuncionario: Int,
        dsTarefas: String
    ): CreateTarefaResult? {

        return kotlin.runCatching{
            val result = api.createTarefa(cdTipoTarefa, dsTarefas, cdFuncionario )
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

        return kotlin.runCatching{
            val result = api.getDepartamentos()
            if (result.isSuccessful){
                result.body()
            }
            else {
                throw Exception("Erro desconhecido")
            }

        } .getOrElse{ e ->
            Log.e(this::class.java.simpleName, "Falha ao buscar departamentos ${e.message}")
            null
        }

    }


}
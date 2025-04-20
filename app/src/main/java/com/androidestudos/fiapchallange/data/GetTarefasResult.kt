package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Calendar


@Serializable
data class GetTarefasResult (
    @SerializedName("cd_tarefas")
    val cdTarefas: Int,
    @SerializedName("ds_tarefas")
    val dsTarefas: String,
    @SerializedName("nr_tempo")
    val nrTempo: Long,
    @SerializedName("nr_dificuldade")
    val nrDificuldade: Int,
    @SerializedName("bt_finalizado")
    val btFinalizado: Int
){
    fun isConcluded(): Boolean{
        return btFinalizado == 1
    }

    val hour: Int
        get() = getHourAndMinuteFromTimestamp().first
    val minute: Int
        get() = getHourAndMinuteFromTimestamp().second

    private fun getHourAndMinuteFromTimestamp(): Pair<Int, Int> {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = nrTempo
        }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return hour to minute
    }
}
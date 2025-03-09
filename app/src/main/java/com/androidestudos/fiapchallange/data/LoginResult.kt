package com.androidestudos.fiapchallange.data

import androidx.compose.ui.text.toUpperCase
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
data class LoginResult (
    @SerializedName("cd_funcionario")
    val cdFuncionario: Int,
    @SerializedName("nm_depto")
    val nmDepto: String,
    @SerializedName("ds_cargo")
    val dsCargo: String,
    @SerializedName("nm_funcionario")
    val nmFuncionario: String
){
    fun isManager() : Boolean {
        return nmDepto.toUpperCase(Locale.getDefault()) == "operacoes".toUpperCase(Locale.getDefault()) &&
                dsCargo.toUpperCase(Locale.getDefault()) == "gerente".toUpperCase(Locale.getDefault())
    }
}
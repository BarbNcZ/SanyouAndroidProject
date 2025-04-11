package com.androidestudos.fiapchallange.ui.models

sealed class LoginEvents {
    data object LoginFailed: LoginEvents()
    data class LoginSuccessfully(val cdFuncionario: Int, val isManager: Boolean): LoginEvents()
}

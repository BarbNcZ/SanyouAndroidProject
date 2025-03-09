package com.androidestudos.fiapchallange.ui.models

sealed class TarefasEvents{
    object DeletedSuccessfully: TarefasEvents()
    object DeletionFailed: TarefasEvents()
    object LoginFailed: TarefasEvents()
    data class LoginSuccessfully(val cdFuncionario: Int, val isManager: Boolean): TarefasEvents()

}

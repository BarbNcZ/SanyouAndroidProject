package com.androidestudos.fiapchallange.ui.models

sealed class TarefasEvents{
    object DeletedSuccessfully: TarefasEvents()
    object DeletionFailed: TarefasEvents()
}

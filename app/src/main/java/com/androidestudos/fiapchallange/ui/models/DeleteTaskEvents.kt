package com.androidestudos.fiapchallange.ui.models

sealed class DeleteTaskEvents {
    object DeletedSuccessfully: DeleteTaskEvents()
    object DeletionFailed: DeleteTaskEvents()
}
package com.androidestudos.fiapchallange.ui.models

sealed class DeleteEmployeeEvents {
    object DeletedSuccessfully: DeleteEmployeeEvents()
    object DeletionFailed: DeleteEmployeeEvents()
}
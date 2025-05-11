package com.androidestudos.fiapchallange.ui.models

sealed class CreateTaskEvents {
    object CreatedSuccessfully: CreateTaskEvents()
    object CreationFailed: CreateTaskEvents()
}
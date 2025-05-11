package com.androidestudos.fiapchallange.ui.models

sealed class CreateEmployeesEvents {
    data object CreateddSuccessfully: CreateEmployeesEvents()
    data object CreationFailed: CreateEmployeesEvents()
}
package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.LoginResult

data class LoginState(
    val user: LoginResult = LoginResult(-1,"","",""),
)
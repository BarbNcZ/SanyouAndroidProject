package com.androidestudos.fiapchallange.ui.view.pages.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LoginScreen(
    login: (String, String) -> Unit
) {
    Text("Email")

    var email by remember {
        mutableStateOf("")
    }

    TextField(email, {email = it})


    Text("Password")

    var password by remember {
        mutableStateOf("")
    }

    TextField(password, {password = it}, visualTransformation = PasswordVisualTransformation())


    Button(onClick = {
        login(email, password)
    }) {
        Text("Log in")
    }
}
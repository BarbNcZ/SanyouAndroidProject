package com.androidestudos.fiapchallange.ui.view.pages.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.R
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme

@Composable
fun LoginScreen(
    login: (String, String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.Center)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().height(124.dp),
                painter = painterResource(R.drawable.sanyou_logo),
                contentDescription = null,
            )

            var email by remember {
                mutableStateOf("")
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                value = email,
                onValueChange = {email = it},
                placeholder = {
                    Text("email@domain.com")
                },
            )

            var password by remember {
                mutableStateOf("")
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                value = password,
                onValueChange = {password = it},
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text("password")
                },
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                onClick = {
                    login(email, password)
                }
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    FiapChallangePreviewTheme {
        LoginScreen { _, _ ->  }
    }
}
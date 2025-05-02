package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.LoginResult
import com.androidestudos.fiapchallange.data.User
import com.androidestudos.fiapchallange.repository.LoginRepository
import com.androidestudos.fiapchallange.ui.models.LoginEvents
import com.androidestudos.fiapchallange.ui.models.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.util.Locale

class LoginViewModel(
    val repository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state: StateFlow<LoginState> = _state
    private val _event = Channel<LoginEvents>()
    val event: Flow<LoginEvents> = _event.receiveAsFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.login(
                User(
                    email,
                    String(Hex.encodeHex(DigestUtils.md5(password))).uppercase(Locale.getDefault())
                )
            )
            val userResult = user.last() ?: LoginResult(-1,"", "", "")
            _state.value = _state.value.copy(
                user = userResult
            )
            if (userResult.cdFuncionario > 0){
                _event.send(
                    LoginEvents.LoginSuccessfully(
                        userResult.cdFuncionario,
                        userResult.isManager()
                    )
                )
            }
            else{
                _event.send(LoginEvents.LoginFailed)
            }

        }
    }
}
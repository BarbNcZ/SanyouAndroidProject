package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.LoginResult
import com.androidestudos.fiapchallange.data.User
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LoginRepository (
    private val dataSource: APIServerDataSource
) {
    fun login(user: User) :Flow<LoginResult?> {

        return flow {
            emit(
                withContext(Dispatchers.IO) {
                    dataSource.login(user)
                }
            )
        }
    }
}
package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.GetCargoResultList
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RolesRepository (
    private val dataSource: APIServerDataSource
) {
    fun getRole() :Flow<GetCargoResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getCargo()
                }
            )
        }
    }
}
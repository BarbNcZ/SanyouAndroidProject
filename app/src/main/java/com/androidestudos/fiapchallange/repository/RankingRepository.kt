package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RankingRepository(
    private val dataSource: APIServerDataSource
) {

    fun getRanking() :Flow<GetFuncionarioResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getRanking()
                }
            )
        }
    }
}
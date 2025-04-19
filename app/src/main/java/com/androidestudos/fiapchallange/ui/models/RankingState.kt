package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList

data class RankingState (
    val funcionarios: List<GetFuncionarioResult> = listOf()
)
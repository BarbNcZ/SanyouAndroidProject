package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetCargoResult

class RoleMapper {
    fun fromGetCargoResultListToListOfGetCargoResult(
        getCargoResultList: Array<GetCargoResult>
    ): List<GetCargoResult> {
        return getCargoResultList.toList()
    }
}
package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetCargoResult

class CargoMapper {
    fun fromGetCargoResultListToListOfGetCargoResult(
        getCargoResultList: Array<GetCargoResult>
    ): List<GetCargoResult> {
        return getCargoResultList.toList()
    }
}
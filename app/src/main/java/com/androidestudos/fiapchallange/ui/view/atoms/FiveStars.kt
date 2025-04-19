package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun FiveStars (

    onEstimationSet: (Int) -> Unit

){

    val star1Activated = remember { mutableStateOf(false) }
    val star2Activated = remember { mutableStateOf(false) }
    val star3Activated = remember { mutableStateOf(false) }
    val star4Activated = remember { mutableStateOf(false) }
    val star5Activated = remember { mutableStateOf(false) }

    val allStars: List<@Composable () -> Unit> = listOf(
        { Star(star1Activated) {

            var estimation = 0

            if(!it){

                estimation = 10

            }
            else{

                estimation = 0

                star2Activated.value = false
                star3Activated.value = false
                star4Activated.value = false
                star5Activated.value = false
            }
            onEstimationSet(estimation)
        } },
        { Star(star2Activated) {

            var estimation = 0

            if (!it){

                estimation = 25

                star1Activated.value = true
            }
            else{

                estimation = 10

                star3Activated.value = false
                star4Activated.value = false
                star5Activated.value = false
            }
            onEstimationSet(estimation)
        } },
        { Star(star3Activated) {

            var estimation = 0

            if (!it){

                estimation = 50

                star1Activated.value = true
                star2Activated.value = true
            }
            else{

                estimation = 25

                star4Activated.value = false
                star5Activated.value = false
            }
            onEstimationSet(estimation)
        } },
        { Star(star4Activated) {

            var estimation = 0

            if (!it){

                estimation = 75

                star1Activated.value = true
                star2Activated.value = true
                star3Activated.value = true
            }
            else{

                estimation = 50

                star5Activated.value = false
            }
            onEstimationSet(estimation)
        } },
        { Star(star5Activated) {

            var estimation = 0

            if (!it){

                estimation = 100

                star1Activated.value = true
                star2Activated.value = true
                star3Activated.value = true
                star4Activated.value = true
            }
            else{

                estimation = 75

            }
            onEstimationSet(estimation)

        } }
    )

    Row {
        allStars.forEach{
            it.invoke()
        }
    }
}
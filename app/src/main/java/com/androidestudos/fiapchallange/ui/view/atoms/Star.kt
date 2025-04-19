package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch

@Composable
fun Star(
    activated: MutableState<Boolean>,

    onClicked: (Boolean) -> Unit

){
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("select_star_animation.json"))
    val lottieAnimatable = rememberLottieAnimatable()
    var hasPlayed by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val startAnimation: () -> Unit = {

        composition?.let {

            hasPlayed = true

            activated.value = true

            scope.launch {
                lottieAnimatable.animate(composition, iterations = 1)
            }

        }

    }

    val reverseAnimation = {
        hasPlayed = false

        activated.value = false

        scope.launch {
            lottieAnimatable.snapTo(progress = 0f)
        }
    }

    if  (activated.value){
        startAnimation()
    }

    else{
        reverseAnimation()
    }

    Box(
        modifier = Modifier
            .clickable {

                onClicked(hasPlayed)

                if (! hasPlayed){


                // On click, play the animation once

                    // You can also use iterations = LottieConstants.IterateForever
                    //LaunchedEffect(Unit) {

                    startAnimation()

                    //}
                }

                else {
                    reverseAnimation()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = androidx.compose.ui.Modifier.size(60.dp)
        )
    }
}
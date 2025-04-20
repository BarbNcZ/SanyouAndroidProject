package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch

@Composable
fun Star(
    activated: MutableState<Boolean>,
    isBetween: () -> Boolean = { false },
    onClicked: (Boolean) -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("select_star_animation.json"))
    val lottieAnimatable = rememberLottieAnimatable()

    val scope = rememberCoroutineScope()

    val startAnimation: () -> Unit = {
        composition?.let {
            activated.value = true

            scope.launch {
                lottieAnimatable.animate(composition, iterations = 1)
            }
        }
    }

    val reverseAnimation = {
        activated.value = false

        scope.launch {
            lottieAnimatable.snapTo(progress = 0f)
        }
    }

    if (activated.value){
        startAnimation()
    } else {
        reverseAnimation()
    }

    Box(
        modifier = Modifier
            .clickable {
                val wasNotBetween = isBetween().not()
                onClicked(activated.value)

                if (!activated.value) {
                    startAnimation()
                } else if (wasNotBetween) {
                    reverseAnimation()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = Modifier.size(60.dp)
        )
    }
}

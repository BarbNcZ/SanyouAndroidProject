package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

private const val STAR_1_INDEX = 0
private const val STAR_1_DIFFICULTY = 10
private const val STAR_2_INDEX = 1
private const val STAR_2_DIFFICULTY = 25
private const val STAR_3_INDEX = 2
private const val STAR_3_DIFFICULTY = 50
private const val STAR_4_INDEX = 3
private const val STAR_4_DIFFICULTY = 75
private const val STAR_5_INDEX = 4
private const val STAR_5_DIFFICULTY = 100

@Composable
fun FiveStars (
    onEstimationSet: (Int) -> Unit
){
    val stars: MutableList<Pair<MutableState<Boolean>, Int>> = mutableListOf()
    for (i in STAR_1_INDEX..STAR_5_INDEX) {
        val starActivated = remember { mutableStateOf(false) }
        stars.add(
            starActivated
            to
            when (i) {
                STAR_1_INDEX -> STAR_1_DIFFICULTY
                STAR_2_INDEX -> STAR_2_DIFFICULTY
                STAR_3_INDEX -> STAR_3_DIFFICULTY
                STAR_4_INDEX -> STAR_4_DIFFICULTY
                else -> STAR_5_DIFFICULTY
            }
        )
    }

    Row {
        stars.forEachIndexed { index, star ->
            val activated = star.first
            val estimated = star.second
            Star(
                activated = activated,
                isBetween = {
                    if (index < stars.lastIndex) {
                        stars[index + 1].first.value
                    } else
                        false
                },
            ) { activated ->
                var estimation: Int

                if (!activated) {
                    estimation = estimated

                    if (index > 0) {
                        for (i in index - 1 downTo 0) {
                            stars[i].first.value = true
                        }
                    }
                } else {
                    val wasNotBetween =
                        (index < stars.lastIndex && stars[index + 1].first.value).not()
                    val indexToConsider = if (wasNotBetween) index - 1 else index

                    estimation = if (indexToConsider > -1) {
                        stars[indexToConsider].second
                    } else 0

                    if (index + 1 <= stars.lastIndex) {
                        for (i in (index + 1) .. stars.lastIndex) {
                            stars[i].first.value = false
                        }
                    }
                }
                onEstimationSet(estimation)
            }
        }
    }
}

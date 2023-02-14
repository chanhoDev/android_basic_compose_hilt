package com.chanho.compose_1.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularIndeterminateProgressBar(isDisplayed:Boolean,verticalBias:Float) {
    if(isDisplayed){
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (progressBar) = createRefs()
            val topBias = createGuidelineFromTop(verticalBias)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressBar){
                    top.linkTo(topBias)
                    start.linkTo(parent.end)
                    end.linkTo(parent.start)
                },
                color = MaterialTheme.colors.primary
            )
        }
    }
}
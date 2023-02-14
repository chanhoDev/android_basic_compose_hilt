package com.chanho.compose_1.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

fun Modifier.cornerRadius10() = graphicsLayer(shape = RoundedCornerShape(10.dp), clip = true)
fun Modifier.cornerRadius40() = graphicsLayer(shape = RoundedCornerShape(40.dp), clip = true)
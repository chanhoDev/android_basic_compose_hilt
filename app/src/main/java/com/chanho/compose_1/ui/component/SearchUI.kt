package com.chanho.compose_1.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chanho.compose_1.data.model.BaseModel
import com.chanho.compose_1.ui.theme.DefaultBackgroundColor
import com.chanho.compose_1.utils.network.DataState

@Composable
fun SearchUI(navController :NavController,searchData:MutableState<DataState<BaseModel>>,itemClick:() -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp,350.dp)
            .padding(start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .background(color = DefaultBackgroundColor)
            .padding(8.dp)
    ){

    }
}
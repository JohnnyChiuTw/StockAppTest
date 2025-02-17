package com.example.stockapptest.version1.presentation.stock_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SingleItemTextValue(
    name: String,
    value: String,
    style: TextStyle,
    color: Color = Color.Black
){

    Row(
        modifier = Modifier
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = name,
            style = style,
            color = color,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "(${value})",
            color = color,
            style = style,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
    }
}
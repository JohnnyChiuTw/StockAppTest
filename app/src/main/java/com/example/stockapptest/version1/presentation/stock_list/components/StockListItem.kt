package com.example.stockapptest.version1.presentation.stock_list.components

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import com.example.stockapptest.R
import com.example.stockapptest.version1.domain.model.StockDay
import com.example.stockapptest.version1.domain.model.StockDayAndAvgForUi
import com.example.stockapptest.version1.domain.model.StockDayAvg

@Composable
fun StockListItem(
    stockDay: StockDay,
    stockAvg: StockDayAvg,
    onItemClick: (StockDay) -> Unit
) {

    ElevatedCard(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),

    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
                .clickable { onItemClick(stockDay) }
                .padding(20.dp),

            ) {

            Text(
                text = "(${stockDay.Code})",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

            Text(
                text = "(${stockDay.Name})",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

            //open and close
            Row(modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.End) {

                Spacer(modifier = Modifier.width(40.dp))

                SingleItemTextValue(
                    name = stringResource(R.string.opening_price_string),
                    value = stockDay.OpeningPrice,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )


                if(stockDay.ClosingPrice.toDouble() > stockAvg.MonthlyAveragePrice.toDouble()) {
                    SingleItemTextValue(
                        name = stringResource(R.string.closing_price_string),
                        value = stockDay.ClosingPrice,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    SingleItemTextValue(
                        name = stringResource(R.string.closing_price_string),
                        value = stockDay.ClosingPrice,
                        color = Color.Green,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }

            //high and low
            Row(modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.End) {
                Spacer(modifier = Modifier.width(40.dp))

                SingleItemTextValue(
                    name = stringResource(R.string.highest_price_string),
                    value = stockDay.HighestPrice,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )

                SingleItemTextValue(
                    name = stringResource(R.string.lowest_price_string),
                    value = stockDay.LowestPrice,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

            //change and avg(from avg api)
            Row(modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.End) {
                Spacer(modifier = Modifier.width(40.dp))

                if(stockDay.Change.toDouble() >=0) {
                    SingleItemTextValue(
                        name = stringResource(R.string.change_string),
                        value = stockDay.Change,
                        color=Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    SingleItemTextValue(
                        name = stringResource(R.string.change_string),
                        value = stockDay.Change,
                        color=Color.Green,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                SingleItemTextValue(
                    name = stringResource(R.string.monthly_ave_price_string),
                    value = stockAvg.MonthlyAveragePrice,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

            //transaction, tradeVolume, tradeValue
            Row(modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.Center) {

                SingleItemTextValueFontSize(
                    name = stringResource(R.string.transaction_string),
                    value = stockDay.Transaction,
                    color = MaterialTheme.colorScheme.tertiary,
                )

                SingleItemTextValueFontSize(
                    name = stringResource(R.string.trade_volume_string),
                    value = stockDay.TradeVolume,
                    color = MaterialTheme.colorScheme.tertiary,
                )

                SingleItemTextValueFontSize(
                    name = stringResource(R.string.trade_value_string),
                    value = stockDay.TradeValue,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }


}

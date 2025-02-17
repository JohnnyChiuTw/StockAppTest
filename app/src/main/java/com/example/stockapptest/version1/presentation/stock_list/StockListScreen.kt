package com.example.stockapptest.version1.presentation.stock_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockapptest.R
import com.example.stockapptest.version1.domain.model.Bwibbu
import com.example.stockapptest.version1.domain.model.StockDay
import com.example.stockapptest.version1.presentation.stock_list.components.StockListItem
import com.example.stockapptest.version1.presentation.stock_list.stock_day.StockDayViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    viewModel: StockDayViewModel = hiltViewModel()
){
    val listSorted = remember {
        mutableStateListOf<StockDay>()
    }
    listSorted.addAll(viewModel.stateDay.value.stockDays)

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val stateDay = viewModel.stateDay.value
    val stateAvg = viewModel.stateDayAvg.value
    val stateBwibbu = viewModel.statebwibbu.value
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var bwibbus: List<Bwibbu> = emptyList()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        if(isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                onDismissRequest = { isSheetOpen = false }) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.decrease_string),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp, top = 8.dp, bottom = 16.dp)
                            .clickable {
                                //todo
                                listSorted.clear()
                                listSorted.addAll(stateDay.stockDays.sortedByDescending { it.Code })
                                isSheetOpen = false
                            }
                    )

                    Text(
                        text = stringResource(R.string.increase_string),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp, top = 8.dp, bottom = 16.dp)
                            .clickable {
                                //todo
                                listSorted.clear()
                                listSorted.addAll(stateDay.stockDays.sortedBy { it.Code })
                                isSheetOpen = false
                            }
                    )

                    Spacer(modifier = Modifier.size(40.dp))
                }
            }
        }

        if(showDialog) {
            if(bwibbus.size>0) {
                val bwibbu = bwibbus[0]
                CustomDialog(bwibbu, setShowDialog = { showDialog = it })
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.height(60.dp).fillMaxWidth().padding(end = 5.dp).background(MaterialTheme.colorScheme.surfaceVariant)){
            Image(
                modifier = Modifier.size(50.dp)
                    .padding(5.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        isSheetOpen = true
                    },
                painter = painterResource(R.mipmap.menu),
                contentDescription = "menu",
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onSurface)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if(!viewModel.isLoading.value) {
                    items(listSorted) { stockDay ->
                        val stockAvgs = stateAvg.stockDayAvgs.toMutableList()
                            .filter { it.Code == stockDay.Code }
                        Log.d("LOG1", "${stockDay.toString()} 個")
                        Log.d("LOG2", "${stockAvgs.size} 個")

                        if(stockAvgs.isNotEmpty()) {
                            val stockAvg = stockAvgs.toMutableList()[0]
                            StockListItem(
                                stockDay = stockDay,
                                stockAvg = stockAvg,
                                onItemClick = {
                                    //todo
                                    bwibbus = stateBwibbu.bwibbus.toMutableList().filter { it.Name == stockDay.Name }
                                    if(bwibbus.size>0) {
                                        showDialog = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
            if(stateDay.error.isNotBlank()) {
                Text(
                    text = stateDay.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if(stateAvg.error.isNotBlank()) {
                Text(
                    text = stateAvg.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if(viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }

    }
}
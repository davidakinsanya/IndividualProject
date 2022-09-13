package com.indieproject.client.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.indieproject.client.db.CardModel
import com.indieproject.client.viewmodel.UpdateViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * This function creates the list view as a composable in the MainActivity.
 *
 * @param cards an object capturing the state a list of CardModel objects.
 * @param state a LazyListState object for manipulating scroll position.
 * @param scope a CoroutineScope object for manipulating scroll position.
 * @param context a Context object.
 */
@Composable
fun ListView(cards: State<List<CardModel>>, state: LazyListState, scope: CoroutineScope, context: Context) {
  Column(
    Modifier
      .fillMaxHeight()
      .fillMaxWidth()
      .padding(top = 100.dp, bottom = 290.dp, start = 30.dp, end = 30.dp)
      .background(color = Color.LightGray, shape = RoundedCornerShape(50.dp))) {
    LazyColumn(
      Modifier
        .fillMaxHeight(1.0f)
        .fillMaxWidth(1.0f)
        .padding(15.dp)
        .background(
          color = Color.Transparent,
          shape = RoundedCornerShape(50.dp)
        ),
      state = state
    )
    {
      scope.launch {
        state.scrollToItem(0)
      }
      itemsIndexed(cards.value) { _, card ->
        LogCard(card = card, context = context)
      }
    }
  }
}

/**
 * A function for creating the log button as a composable in the MainActivity.
 *
 * @param update an UpdateViewModel object.
 */
@Composable
fun LogButtonView(update: UpdateViewModel) {
  Column(modifier = Modifier.padding(top = 35.dp, start = 160.dp)) {
    LogButton(update = update)
  }
}

/**
 * A function for creating the menu of buttons as a composable in the MainActivity.
 *
 * @param update an UpdateViewModel object.
 */
@Composable
fun ButtonView(update: UpdateViewModel) {
  Column(modifier = Modifier.padding(top = 530.dp, start = 65.dp)) {
    Row(modifier = Modifier.padding()) {
      Column(modifier = Modifier.padding()) {
        MonButton(update = update)
      }

      Column(modifier = Modifier.padding(start = 30.dp)) {
        EnvButton(update = update)
      }

      Column(modifier = Modifier.padding(start = 30.dp)) {
        DangerButton(update = update)
      }

    }
    Column(modifier = Modifier.padding(top = 30.dp, start = 99.dp)) {
      RefreshButton(update = update)
    }
  }
}
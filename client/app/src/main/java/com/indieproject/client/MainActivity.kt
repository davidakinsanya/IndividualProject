package com.indieproject.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.indieproject.client.db.DbHelper
import com.indieproject.client.repository.MonitorRepository
import com.indieproject.client.repository.EnvRepository
import com.indieproject.client.ui.theme.ClientTheme
import com.indieproject.client.view.*
import com.indieproject.client.viewmodel.CardViewModel
import com.indieproject.client.viewmodel.UpdateViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ClientTheme {
        Surface(color = MaterialTheme.colors.background) {
          val db = DbHelper(this)
          db.deleteDatabase()

          val env = EnvRepository()
          val mon = MonitorRepository()
          val model = CardViewModel(mon, env, db)
          val ui = UpdateViewModel(model, db)
          val cards = ui.bootCards.collectAsState()

          val state = rememberLazyListState()
          val coroutineScope = rememberCoroutineScope()

          LogButtonView(update = ui)
          ListView(cards = cards, state = state, scope = coroutineScope, context = applicationContext)
          ButtonView(update = ui)
        }
      }
    }
  }
}
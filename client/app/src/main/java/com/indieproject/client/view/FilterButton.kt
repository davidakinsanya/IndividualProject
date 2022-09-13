package com.indieproject.client.view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.indieproject.client.viewmodel.UpdateViewModel

/**
 * This function forms a button to help the user see a only monitor data related
 * log messages interpreted by the system.
 *
 * @param update an UpdateViewModel object.
 */
@Composable
fun MonButton(update: UpdateViewModel) {
  Button(modifier = Modifier, onClick = { update.getMonCards() }) {
    Text("MON")
  }
}

/**
 * This function forms a button to help the user see a only environment data related
 * log messages interpreted by the system.
 *
 * @param update an UpdateViewModel object.
 */
@Composable
fun EnvButton(update: UpdateViewModel) {
  Button(modifier = Modifier, onClick = { update.getEnvCards() }) {
    Text("ENV")
  }
}

/**
 * This function forms a button to help the user see a only
 * log messages in danger status interpreted by the system.
 *
 * @param update an UpdateViewModel object.
 */
@Composable
fun DangerButton(update: UpdateViewModel) {
  Button(modifier = Modifier, onClick = { update.dangerCards() }) {
    Text("RED")
  }
}

/**
 * This function forms a button to help the user see all
 * log messages interpreted by the system in the current session.
 *
 * @param update an UpdateViewModel object.
 */
@Composable
fun RefreshButton(update: UpdateViewModel) {
  Button(modifier = Modifier, onClick = { update.refreshCards() }) {
    Text("F5")
  }
}

  /**
   * This function forms a button to help the user start a new session.
   *
   * @param update an UpdateViewModel object.
   */
  @Composable
  fun LogButton(update: UpdateViewModel) {
    Button(modifier = Modifier, onClick = { update.newSession() }) {
      Text("LOG")
    }
  }
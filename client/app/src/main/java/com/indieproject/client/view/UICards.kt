package com.indieproject.client.view


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indieproject.client.MainActivity
import com.indieproject.client.data.iot.EnvironmentData
import com.indieproject.client.data.iot.MonitorData
import com.indieproject.client.db.CardModel

/**
 * This function forms a UI Card to help the user see a snippet
 * of the log message interpreted by the system.
 *
 * @param card a CardModel object.
 * @param context the context of which to display the UI Card.
 *
 * @author david
 */
@Composable
fun LogCard(card: CardModel, context: Context) {
  val color: Color =  if (card.dangerStatus == 1)
                      { Color.Red } else Color.Black

    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .height(60.dp)
        .clickable { Toast.makeText(context, card.log, Toast.LENGTH_SHORT).show() },
      elevation = 10.dp,
    ) {
      Row(
        modifier = Modifier.padding(10.dp)
      ) {
        Column(Modifier.padding(top = 10.dp),
          verticalArrangement = Arrangement.Center
          , horizontalAlignment = Alignment.CenterHorizontally) {
           Text(card.objectType , Modifier.size(36.dp), color = color)
        }
        Spacer(Modifier.padding(start = 160.dp))

        Column(verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "View log", Modifier.size(40.dp), color = color)
        }
      }
    }
}
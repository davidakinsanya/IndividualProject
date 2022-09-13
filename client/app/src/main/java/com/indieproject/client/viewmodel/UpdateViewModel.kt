package com.indieproject.client.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indieproject.client.db.CardModel
import com.indieproject.client.db.DbHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * This class serves as a ViewModel intended to
 * manage the state of log data displayed in the MainActivity.
 *
 * @param card a CardViewModel object.
 * @param db a DbHelper object.
 *
 * @author david.
 */
class UpdateViewModel constructor (private val card: CardViewModel, private val db: DbHelper): ViewModel() {

  private val firstBoot = mutableListOf<CardModel>()
  private val _bootCards = MutableStateFlow(listOf<CardModel>())
  val bootCards: StateFlow<List<CardModel>> get() = _bootCards

  init {
    getCards()
  }

  /**
   * This starts a new session of logging data.
   */
  fun newSession() {
    db.deleteDatabase()
    getCards()
  }

  /**
   * This function launches retrieves log messages and appends
   * them to a live data object retrievable in the MainActivity.
   */
  private fun getCards() {
    viewModelScope.launch(Dispatchers.Default) {
      repeat(25) {
        card.getBootCards()
      }
      delay(2000)
      val cards = db.getAllCards()
      firstBoot += cards
      _bootCards.emit(cards)
    }
  }

  /**
   * This function launches retrieves monitor log messages and appends
   * them to a live data object retrievable in the MainActivity.
   */
  fun getMonCards() {
    val monCards = mutableListOf<CardModel>()

    viewModelScope.launch(Dispatchers.Default) {
      for (card in firstBoot) if (card.objectType == "MON") monCards += card
      delay(250)
      _bootCards.emit(monCards)
    }
  }

  /**
   * This function launches retrieves environment log messages and appends
   * them to a live data object retrievable in the MainActivity.
   */
  fun getEnvCards() {
    val envCards = mutableListOf<CardModel>()

    viewModelScope.launch(Dispatchers.Default) {
      for (card in firstBoot) if (card.objectType == "ENV") envCards += card
      delay(250)
      _bootCards.emit(envCards)
    }
  }

  /**
   * This function launches retrieves session log messages and appends
   * them to a live data object retrievable in the MainActivity.
   */
  fun refreshCards() {
    viewModelScope.launch(Dispatchers.Default) {
      delay(750)
      _bootCards.emit(firstBoot)
    }
  }

  /**
   * This function launches retrieves danger status log messages and appends
   * them to a live data object retrievable in the MainActivity.
   */
  fun dangerCards() {
    viewModelScope.launch(Dispatchers.Default) {
      val dangerCards = mutableListOf<CardModel>()
      for (card in firstBoot) {
        if (card.dangerStatus == 1)
          dangerCards += card
      }
      delay(250)
      _bootCards.emit(dangerCards)
    }
  }
}
package com.indieproject.client.db

/**
 * This class models the data used for UI Cards.
 *
 * @author david
 */
class CardModel {
  var id : Int = 0
  var objectType : String = ""
  var log : String = ""
  var dangerStatus: Int = 0

  /**
   * This function is tasked with creating a CardModel object after successfully logging a message.
   *
   * @param id the identifier of the object associated with the log message.
   * @param objectType the type of object associated with the log message.
   * @param log the log message.
   * @param danger an integer indicated of the possible severity of the log message.
   *
   * @return a CardModel object derived from the function parameters.
   */
  fun createCard(id: Int, objectType: String, log: String, danger: Int): CardModel {
    val card = CardModel()
    card.id = id
    card.objectType = objectType
    card.log = log
    card.dangerStatus = danger

    return card
  }
}
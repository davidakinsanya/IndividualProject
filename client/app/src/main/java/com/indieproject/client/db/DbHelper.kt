package com.indieproject.client.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlinx.coroutines.selects.select

/**
 * This class control the movement of application data to be used in the user interface.
 *
 * @param context the context of which to perform database operations.
 * @author david
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
  companion object {
    private const val DB_NAME = "log_card_objects"
    private const val DB_VERSION = 1
    private const val TABLE_NAME = "log_cards"
    private const val ID = "id"
    private const val OBJECT_TYPE = "object"
    private const val LOG = "log"
    private const val DANGER_STATUS = "danger_status"
  }

  override fun onCreate(p0: SQLiteDatabase?) {
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $OBJECT_TYPE TEXT, $LOG TEXT, $DANGER_STATUS INTEGER DEFAULT 0);"
    p0?.execSQL(CREATE_TABLE)
  }

  override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    p0?.execSQL(DROP_TABLE)
    onCreate(p0)
  }

  private fun createCard(cursor: Cursor): CardModel {
    val card = CardModel()
    card.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
    card.objectType = cursor.getString(cursor.getColumnIndexOrThrow(OBJECT_TYPE))
    card.log = cursor.getString(cursor.getColumnIndexOrThrow(LOG))
    card.dangerStatus = cursor.getInt(cursor.getColumnIndexOrThrow(DANGER_STATUS))
    return card
  }

  /**
   * This function retrieves all card objects from the database.
   *
   * @return a list of cards.
   */
  fun getAllCards(): List<CardModel> {
    val cardList = ArrayList<CardModel>()
    val db = writableDatabase
    val selectQuery = "SELECT * FROM $TABLE_NAME"
    val cursor = db.rawQuery(selectQuery, null)
    if (cursor != null) {
      if (cursor.moveToFirst()) {
        do {
          cardList.add(createCard(cursor))
        }
         while (cursor.moveToNext())
      }
    }
    cursor.close()
    return cardList
  }

  /**
   * This method adds a card to the database.
   *
   * @param card a new CardModel object.
   * @return a boolean representing whether or not the card had successfully been added.
   */
  fun addCard(card: CardModel): Boolean {
    val db = this.writableDatabase
    val values = ContentValues()
    values.put(OBJECT_TYPE, card.objectType)
    values.put(LOG, card.log)
    values.put(DANGER_STATUS, card.dangerStatus)
    val success: Long = db.insert(TABLE_NAME, null, values)
    db.close()
    return (Integer.parseInt("$success") != -1)
  }

  /**
   * This method gets a specific card from the database.
   *
   * @param id the id of the desired card.
   * @return a CardModel object.
   */
  fun getCard(id: Int): CardModel {
    val db = writableDatabase
    val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $id"
    val cursor = db.rawQuery(selectQuery, null)

    cursor?.moveToFirst()
    val card = createCard(cursor)
    cursor.close()
    return card
  }

  /**
   * This method deletes a specific card from the database.
   *
   * @param id the id of the desired card.
   * @return a boolean representing whether or not the card had successfully been deleted.
   */
  fun deleteCard(id: Int): Boolean {
    val db = writableDatabase
    val success: Long = db.delete(TABLE_NAME, "=?", arrayOf(id.toString())).toLong()
    db.close()
    return (Integer.parseInt("$success") != -1)
  }

  /**
   * This method deletes the database table from the devices internal memory.
   */
  fun deleteDatabase() {
    val db = writableDatabase
    val deleteQuery = "DELETE FROM $TABLE_NAME"
    db.execSQL(deleteQuery)
  }
}
package com.example.bankapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.*
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class BankDB(context: Context, factory: CursorFactory?) : SQLiteOpenHelper(context,
    DB_NAME,factory,1){

    companion object{
        const val DB_NAME = "bank_db"
        const val CUSTOMERS_TABLE = "customers"
        const val TRANSFERS_TABLE = "transfers"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $CUSTOMERS_TABLE(name text, email text primary key, current_balance decimal)")
        db?.execSQL("create table $TRANSFERS_TABLE(id integer primary key autoincrement not null, sender text, receiver text, amount decimal)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists $CUSTOMERS_TABLE")
        db?.execSQL("drop table if exists $TRANSFERS_TABLE")
        onCreate(db)
    }

    fun insertCustomer(customer: Customer){
        val cv = ContentValues()
        cv.put("name",customer.name)
        cv.put("email", customer.email)
        cv.put("current_balance", customer.currentBalance)
        val db = this.writableDatabase
        db.insertWithOnConflict(CUSTOMERS_TABLE,null,cv, CONFLICT_IGNORE)
        db.close()
    }

    fun makeTransfer(transfer: Transfer){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("sender",transfer.sender)
        cv.put("receiver",transfer.receiver)
        cv.put("amount",transfer.amount)
        db.insert(TRANSFERS_TABLE,null,cv)
        db.execSQL("update $CUSTOMERS_TABLE set current_balance = current_balance + ${transfer.amount} where email = \"${transfer.receiver}\"")
        db.execSQL("update $CUSTOMERS_TABLE set current_balance = current_balance - ${transfer.amount} where email = \"${transfer.sender}\"")
        db.execSQL("delete from $CUSTOMERS_TABLE where current_balance < 0")
        db.close()
    }

    fun getAllCustomers(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("select * from $CUSTOMERS_TABLE",null)
    }

    fun getAllTransfers(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("select * from $TRANSFERS_TABLE",null)
    }

}
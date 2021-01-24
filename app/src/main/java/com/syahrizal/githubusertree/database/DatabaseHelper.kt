package com.syahrizal.githubusertree.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.DESC
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.HTML_URL
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.ID_REPOS
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.LANGUAGE
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.NAME
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.STAR_COUNT
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.TABLE_REPOS
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.TIME_UPDATE
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.AVATAR
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.HTML
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.ID
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.TABLE_USER
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.USERNAME


class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "dbgithub"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER" +
                "($ID INTEGER PRIMARY KEY," +
                "$USERNAME TEXT NOT NULL, " +
                "$AVATAR TEXT NOT NULL," +
                "$HTML TEXT NOT NULL)"

        private const val SQL_CREATE_TABLE_REPOS = "CREATE TABLE $TABLE_REPOS" +
                "($ID_REPOS INTEGER PRIMARY KEY," +
                "$NAME TEXT NOT NULL," +
                "$DESC TEXT NOT NULL," +
                "$LANGUAGE TEXT NOT NULL," +
                "$HTML_URL TEXT NOT NULL," +
                "$STAR_COUNT INTEGER," +
                "$TIME_UPDATE TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_USER)
        db.execSQL(SQL_CREATE_TABLE_REPOS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REPOS")
        onCreate(db)
    }

}
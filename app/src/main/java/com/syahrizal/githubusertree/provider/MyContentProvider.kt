package com.syahrizal.githubusertree.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.syahrizal.githubusertree.database.UserContract.AUTHOR_USER
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.CONTENT_URI_USER
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.TABLE_USER
import com.syahrizal.githubusertree.database.UserHelper

class MyContentProvider : ContentProvider() {


    companion object {
        private const val USER = 1
        private const val USER_ID = 2
        private lateinit var userHelper: UserHelper

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHOR_USER, TABLE_USER, USER)
            uriMatcher.addURI(AUTHOR_USER, "$TABLE_USER/#", USER_ID)
        }
    }

    override fun onCreate(): Boolean {
        userHelper = UserHelper.getDatabase(context as Context)
        userHelper.open()
        return true

    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        var cursor: Cursor? = null
        when (uriMatcher.match(uri)) {
            USER -> cursor = userHelper.queryAll()
            USER_ID -> userHelper.queryById(uri.lastPathSegment.toString())

            else -> cursor = null
        }

        return cursor

    }


    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (USER) {
            uriMatcher.match(uri) -> userHelper.insert(values)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI_USER, null)
        return Uri.parse("$CONTENT_URI_USER/$added")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val update: Int = when (USER_ID) {
            uriMatcher.match(uri) -> userHelper.update(uri.lastPathSegment.toString(), values)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI_USER, null)

        return update
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val delete = when (USER_ID) {
            uriMatcher.match(uri) -> userHelper.delete(uri.lastPathSegment.toString())
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI_USER, null)
        return delete
    }
}
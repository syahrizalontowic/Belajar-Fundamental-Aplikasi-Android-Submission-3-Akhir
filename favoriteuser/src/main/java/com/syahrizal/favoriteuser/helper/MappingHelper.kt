package com.syahrizal.favoriteuser.helper

import android.database.Cursor
import com.syahrizal.favoriteuser.database.UserContract.UserColumns.Companion.AVATAR
import com.syahrizal.favoriteuser.database.UserContract.UserColumns.Companion.HTML
import com.syahrizal.favoriteuser.database.UserContract.UserColumns.Companion.ID
import com.syahrizal.favoriteuser.database.UserContract.UserColumns.Companion.USERNAME
import com.syahrizal.favoriteuser.model.user.User

object MappingHelper {

    fun mapCursorToList(cursor: Cursor?): List<User> {
        val userList = ArrayList<User>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val avatar = getString(getColumnIndexOrThrow(AVATAR))
                val html = getString(getColumnIndexOrThrow(HTML))
                userList.add(User(username, avatar, html, id))
            }
        }

        return userList
    }

}
package com.syahrizal.githubusertree.helper

import android.database.Cursor
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.DESC
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.HTML_URL
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.ID_REPOS
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.LANGUAGE
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.NAME
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.STAR_COUNT
import com.syahrizal.githubusertree.database.ReposContract.ReposColumns.Companion.TIME_UPDATE
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.AVATAR
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.HTML
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.ID
import com.syahrizal.githubusertree.database.UserContract.UserColumns.Companion.USERNAME
import com.syahrizal.githubusertree.model.repository.Repository
import com.syahrizal.githubusertree.model.user.User

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

    fun mapCursorToListStar(cursor: Cursor?): List<Repository> {
        val reposList = ArrayList<Repository>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ID_REPOS))
                val name = getString(getColumnIndexOrThrow(NAME))
                val desc = getString(getColumnIndexOrThrow(DESC))
                val language = getString(getColumnIndexOrThrow(LANGUAGE))
                val star = getInt(getColumnIndexOrThrow(STAR_COUNT))
                val time = getString(getColumnIndexOrThrow(TIME_UPDATE))
                val url = getString(getColumnIndexOrThrow(HTML_URL))
                reposList.add(Repository(star, language, id, url, name, desc, time))
            }
        }

        return reposList
    }
}
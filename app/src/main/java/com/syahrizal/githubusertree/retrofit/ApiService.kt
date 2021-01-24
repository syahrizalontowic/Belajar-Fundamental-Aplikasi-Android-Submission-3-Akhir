package com.syahrizal.githubusertree.retrofit

import com.syahrizal.githubusertree.model.detailUser.DetailUser
import com.syahrizal.githubusertree.model.repository.Repository
import com.syahrizal.githubusertree.model.user.ResponseUser
import com.syahrizal.githubusertree.model.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization:9637db7fca53793312a5b1f3c249bedbdafdbe41")
    fun getSearchData(
        @Query("q") query: String
    ): Call<ResponseUser>


    @GET("users/{username}")
    @Headers("Authorization:9637db7fca53793312a5b1f3c249bedbdafdbe41")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUser>


    @GET("users/{username}/followers")
    @Headers("Authorization:9637db7fca53793312a5b1f3c249bedbdafdbe41")
    fun getFollowersData(
        @Path("username") username: String,
        @Query("page") page: String
    ): Call<List<User>>


    @GET("users/{username}/following")
    @Headers("Authorization:9637db7fca53793312a5b1f3c249bedbdafdbe41")
    fun getFollowingData(
        @Path("username") username: String,
        @Query("page") page: String
    ): Call<List<User>>

    @GET("users/{username}/repos")
    @Headers("Authorization:9637db7fca53793312a5b1f3c249bedbdafdbe41")
    fun getRepositoryData(
        @Path("username") username: String
    ): Call<List<Repository>>

}
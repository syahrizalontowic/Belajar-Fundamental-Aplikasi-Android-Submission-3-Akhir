package com.syahrizal.favoriteuser.viewmodel

import android.content.Context
import android.telecom.Call
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syahrizal.favoriteuser.model.user.ResponseUser
import com.syahrizal.favoriteuser.model.user.User
import com.syahrizal.favoriteuser.retrofit.RetroConfig
import retrofit2.Response

class BaseViewModel : ViewModel() {


    private val listUser : MutableLiveData<List<User>> = MutableLiveData()

    fun loadUser(context:Context,query:String){
        RetroConfig.getRetrofit().getSearchData(query).enqueue(object : retrofit2.Callback<ResponseUser>{
            override fun onFailure(call: retrofit2.Call<ResponseUser>, t: Throwable) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<ResponseUser>, response: Response<ResponseUser>) {
                if(response.body() != null){
                    listUser.postValue(response.body()?.items)
                }
            }
        })
    }

    val getListUser : LiveData<List<User>> = listUser
}
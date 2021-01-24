package com.syahrizal.favoriteuser.viewmodel

import android.content.Context
import android.telecom.Call
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syahrizal.favoriteuser.model.detailUser.DetailUser
import com.syahrizal.favoriteuser.retrofit.RetroConfig
import retrofit2.Response

class DetailViewModel : ViewModel() {


    private val userDetail : MutableLiveData<DetailUser> = MutableLiveData()

    fun loadUser(context: Context, username:String){
        RetroConfig.getRetrofit().getDetailUser(username).enqueue(object :retrofit2.Callback<DetailUser>{
            override fun onFailure(call: retrofit2.Call<DetailUser>, t: Throwable) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<DetailUser>, response: Response<DetailUser>) {
                if (response.body() != null){
                    userDetail.value = response.body()
                }
            }
        })
    }

    val getDetailUser : LiveData<DetailUser> = userDetail
}
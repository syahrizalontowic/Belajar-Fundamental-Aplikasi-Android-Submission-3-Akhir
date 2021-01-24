package com.syahrizal.githubusertree.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.syahrizal.githubusertree.R
import com.syahrizal.githubusertree.adapter.UserAdapter
import com.syahrizal.githubusertree.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {


    private lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        val query = intent.getStringExtra("QUERY")
        setSupportActionBar(findViewById(R.id.toolbar))

        if (query != null) {
            baseViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(BaseViewModel::class.java)
            baseViewModel.loadUser(applicationContext, query)
            baseViewModel.getListUser.observe(this, Observer {
                rvUser.adapter = UserAdapter(applicationContext, it)
            })

            tvToolbar.text = query
            rvUser.layoutManager = LinearLayoutManager(this)
        }

        btFavorite.setOnClickListener {
            startActivity(Intent(applicationContext, FavoriteActivity::class.java))
        }
        btSettings.setOnClickListener {
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }
    }
}

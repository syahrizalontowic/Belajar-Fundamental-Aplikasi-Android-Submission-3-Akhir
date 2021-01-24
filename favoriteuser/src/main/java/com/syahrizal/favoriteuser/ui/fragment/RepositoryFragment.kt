package com.syahrizal.favoriteuser.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.syahrizal.favoriteuser.R
import com.syahrizal.favoriteuser.adapter.ReposAdapter
import com.syahrizal.favoriteuser.model.user.User
import com.syahrizal.favoriteuser.ui.activity.DetailActivity
import com.syahrizal.favoriteuser.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.fragment_repository.*

class RepositoryFragment : Fragment() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRepos()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setRepos() {

        val user = activity!!.intent.getParcelableExtra<User>(DetailActivity.EXTRA_USER)
        if (user != null) {

            repositoryViewModel =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                    RepositoryViewModel::class.java
                )
            context?.let { repositoryViewModel.loadRepos(it, user.login) }
            repositoryViewModel.getRepos.observe(viewLifecycleOwner, Observer {
                rvRepos.adapter = ReposAdapter(context, it)
                linear_button.visibility = View.VISIBLE
            })

            rvRepos.layoutManager = LinearLayoutManager(context)
        }
    }
}
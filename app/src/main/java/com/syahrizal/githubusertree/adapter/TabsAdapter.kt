package com.syahrizal.githubusertree.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.syahrizal.githubusertree.ui.fragment.FollowersFragment
import com.syahrizal.githubusertree.ui.fragment.FollowingFragment
import com.syahrizal.githubusertree.ui.fragment.RepositoryFragment

class TabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RepositoryFragment()
            1 -> return FollowersFragment()
            2 -> return FollowingFragment()
        }
        return RepositoryFragment()
    }

}
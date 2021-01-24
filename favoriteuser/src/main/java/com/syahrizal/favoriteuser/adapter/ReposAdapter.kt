package com.syahrizal.favoriteuser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syahrizal.favoriteuser.R
import com.syahrizal.favoriteuser.model.repository.Repository

class ReposAdapter(private val context: Context?, private val reposList: List<Repository>) :
    RecyclerView.Adapter<ReposAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.tvNameRepo)
        private val desc = view.findViewById<TextView>(R.id.tvDescRepo)
        private val language = view.findViewById<TextView>(R.id.tvLanguage)
        private val star = view.findViewById<TextView>(R.id.tvStar)
        private val time = view.findViewById<TextView>(R.id.tvTimeRepo)

        @SuppressLint("SetTextI18n")
        fun bind(repos: Repository) {

            name.text = repos.name
            desc.text = repos.description
            language.text = repos.language
            star.text = repos.stargazersCount.toString()
            time.text = "Update " + repos.updatedAt


        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reposList[position])
        holder.itemView.setOnClickListener {

        }
    }
}
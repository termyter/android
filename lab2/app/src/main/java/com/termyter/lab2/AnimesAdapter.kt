package com.termyter.lab2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.termyter.lab2.databinding.PictureItemBinding

class AnimesAdapter: RecyclerView.Adapter<AnimesAdapter.PictureViewHolder>() {
    private var pictureList = ArrayList<Anime>()
    class PictureViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = PictureItemBinding.bind(item)
        fun bind(anime: Anime) = with(binding){
            card1Title.text = anime.name
            card1Photo.setImageResource(anime.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictureList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, InfoAnimeActivity::class.java)
            intent.putExtra("item", pictureList[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pictureList.size
    }

    fun addPicture(_animeList: ArrayList<Anime>){
        pictureList = _animeList
        notifyDataSetChanged()
    }
}
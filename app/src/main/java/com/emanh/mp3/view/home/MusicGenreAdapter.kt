package com.emanh.mp3.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emanh.mp3.R
import com.emanh.mp3.databinding.ViewholderMusicGenreBinding
import com.emanh.mp3.model.GenreModel
import com.google.android.material.imageview.ShapeableImageView

class MusicGenreAdapter(
    private val items: MutableList<GenreModel>
) : RecyclerView.Adapter<MusicGenreAdapter.ViewHolder>() {

    private var content: Context? = null

    class ViewHolder(val binding: ViewholderMusicGenreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        content = parent.context
        val binding =
            ViewholderMusicGenreBinding.inflate(LayoutInflater.from(content), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        loadLogo(holder, position, holder.binding.imgLogoSong1, 0)
        loadLogo(holder, position, holder.binding.imgLogoSong2, 1)
        loadLogo(holder, position, holder.binding.imgLogoSong3, 2)
        loadLogo(holder, position, holder.binding.imgLogoSong4, 3)

        holder.binding.txtMusicGenre.text = item.musicGenre
    }

    private fun loadLogo(holder: ViewHolder, position: Int, image: ShapeableImageView, i: Int) {
        Glide.with(holder.itemView)
            .load(items[position].song[i].logo)
            .error(R.drawable.ic_logo)
            .into(image)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<GenreModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
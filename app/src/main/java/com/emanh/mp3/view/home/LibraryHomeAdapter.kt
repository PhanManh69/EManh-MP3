package com.emanh.mp3.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emanh.mp3.R
import com.emanh.mp3.databinding.ViewholderLibraryHomeBinding
import com.emanh.mp3.model.LibraryModel
import com.google.android.material.imageview.ShapeableImageView

class LibraryHomeAdapter(
    private val items: MutableList<LibraryModel>
) : RecyclerView.Adapter<LibraryHomeAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(val binding: ViewholderLibraryHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            ViewholderLibraryHomeBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        loadLogo(holder, position, holder.binding.imgLogoSong1, 0)
        loadLogo(holder, position, holder.binding.imgLogoSong2, 1)
        loadLogo(holder, position, holder.binding.imgLogoSong3, 2)
        loadLogo(holder, position, holder.binding.imgLogoSong4, 3)

        Glide.with(holder.itemView)
            .load(item.avatarUser)
            .error(R.drawable.ic_logo)
            .into(holder.binding.imgAvatarUser)

        holder.binding.txtNameLibrary.text = item.nameLibrary
        holder.binding.txtNameUser.text = item.nameUser
    }

    private fun loadLogo(holder: ViewHolder, position: Int, image: ShapeableImageView, i: Int) {
        Glide.with(holder.itemView)
            .load(items[position].song[i].logo)
            .error(R.drawable.ic_logo)
            .into(image)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<LibraryModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
package com.emanh.mp3.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.emanh.mp3.databinding.ViewholderListenAgainBinding
import com.emanh.mp3.model.ListenAgainModel

class ListenAgainAdapter(
    private val items: MutableList<ListenAgainModel>
) : RecyclerView.Adapter<ListenAgainAdapter.ViewHolder>() {

    private var content: Context? = null

    class ViewHolder(val binding: ViewholderListenAgainBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        content = parent.context
        val binding =
            ViewholderListenAgainBinding.inflate(LayoutInflater.from(content), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Glide.with(holder.itemView.context)
            .load(item.logo)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.imgLogoSong)
        holder.binding.txtNameSong.text = item.name
        holder.binding.txtSingerSong.text = item.singer
    }
}
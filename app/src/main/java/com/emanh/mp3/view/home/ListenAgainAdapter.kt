package com.emanh.mp3.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.emanh.mp3.R
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

        holder.binding.progressLogo.visibility = View.VISIBLE
        holder.binding.imgLogoSong.visibility = View.GONE

        Glide.with(holder.itemView.context)
            .load(item.logo)
            .error(R.drawable.ic_logo)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.progressLogo.visibility = View.GONE
                    holder.binding.imgLogoSong.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.progressLogo.visibility = View.GONE
                    holder.binding.imgLogoSong.visibility = View.VISIBLE
                    return false
                }
            })
            .into(holder.binding.imgLogoSong)
        holder.binding.txtNameSong.text = item.name
        holder.binding.txtSingerSong.text = item.singer
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<ListenAgainModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
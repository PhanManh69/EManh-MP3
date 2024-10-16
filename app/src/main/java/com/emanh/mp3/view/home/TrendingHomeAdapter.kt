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
import com.emanh.mp3.databinding.ViewholderQuickPicksBinding
import com.emanh.mp3.model.SongModel

class TrendingHomeAdapter(
    private val items: MutableList<SongModel>
) : RecyclerView.Adapter<TrendingHomeAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(val binding: ViewholderQuickPicksBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            ViewholderQuickPicksBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
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
        holder.binding.txtSingerSong.text = "${item.singer}  •  ${formatViewCount(item.view)}"
    }

    private fun formatViewCount(count: Long): String {
        return when {
            count >= 1_000_000_000 -> String.format("%.1fB", count / 1_000_000_000.0)
            count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000.0)
            count >= 1_000 -> String.format("%.1fK", count / 1_000.0)
            else -> count.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<SongModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
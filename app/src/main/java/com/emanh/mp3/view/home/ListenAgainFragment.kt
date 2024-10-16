package com.emanh.mp3.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.emanh.mp3.R
import com.emanh.mp3.databinding.FragmentListenAgainBinding
import com.emanh.mp3.helper.BaseFragment
import com.emanh.mp3.view.MainActivity
import com.emanh.mp3.view.search.SearchFragment
import com.emanh.mp3.viewModel.SongViewModel
import com.google.android.material.imageview.ShapeableImageView

class ListenAgainFragment : BaseFragment() {

    private var _binding: FragmentListenAgainBinding? = null
    private val binding get() = _binding!!
    private val listenAgainViewModel: SongViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListenAgainBinding.inflate(inflater, container, false)

        initClick()
        initListenAgain()
        initDisplayLogoSong()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun initClick() {
        binding.imgButtonBack.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(HomeFragment())
        }

        binding.imgButtonSearch.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(SearchFragment())
        }
    }

    private fun initListenAgain() {
        val listenAgainAdapter = ListListenAdapter(mutableListOf())
        binding.listListenAgain.layoutManager = LinearLayoutManager(requireContext())
        binding.listListenAgain.adapter = listenAgainAdapter
        binding.progressListenAgain.visibility = View.VISIBLE

        listenAgainViewModel.listenAgainList.observe(viewLifecycleOwner, Observer {
            listenAgainAdapter.updateList(it)

            binding.listListenAgain.visibility = View.VISIBLE
            binding.progressListenAgain.visibility = View.GONE
        })
    }

    private fun initDisplayLogoSong() {
        setGlideImage(binding.imgLogoSong1, 0)
        setGlideImage(binding.imgLogoSong2, 1)
        setGlideImage(binding.imgLogoSong3, 2)
        setGlideImage(binding.imgLogoSong4, 3)
    }

    private fun setGlideImage(image: ShapeableImageView, i: Int) {
        listenAgainViewModel.listenAgainList.observe(viewLifecycleOwner, Observer {
            if (i < it.size) {
                Glide.with(requireContext())
                    .load(it[i].logo)
                    .error(R.drawable.ic_logo)
                    .into(image)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_logo)
                    .into(image)
            }
        })
    }
}
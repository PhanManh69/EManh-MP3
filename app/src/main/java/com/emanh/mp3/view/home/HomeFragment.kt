package com.emanh.mp3.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.emanh.mp3.databinding.FragmentHomeBinding
import com.emanh.mp3.helper.BaseFragment
import com.emanh.mp3.view.MainActivity
import com.emanh.mp3.viewModel.ChannelViewModel
import com.emanh.mp3.viewModel.GenreViewModel
import com.emanh.mp3.viewModel.LibraryViewModel
import com.emanh.mp3.viewModel.SongViewModel

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val songViewModel: SongViewModel by viewModels()
    private val libraryViewModel: LibraryViewModel by viewModels()
    private val channelViewModel: ChannelViewModel by viewModels()
    private val genreViewModel: GenreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initClick()
        initListenAgain()
        initQuickPicks()
        initLibrary()
        initTrending()
        initFollow()
        initMusicGenre()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun initClick() {
        binding.txtButtonAllListen.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(ListenAgainFragment())
        }
    }

    private fun initListenAgain() {
        val listenAgainAdapter = ListenAgainAdapter(mutableListOf())
        binding.listListenAgain.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.listListenAgain.adapter = listenAgainAdapter
        binding.progressListenAgain.visibility = View.VISIBLE

        songViewModel.listenAgainList.observe(viewLifecycleOwner, Observer {
            val limitedList = it.take(10).toMutableList()
            listenAgainAdapter.updateList(limitedList)

            binding.listListenAgain.visibility = View.VISIBLE
            binding.progressListenAgain.visibility = View.GONE
        })
    }

    private fun initQuickPicks() {
        val quickPicksAdapter = QuickPicksAdapter(mutableListOf())
        val layoutManager = GridLayoutManager(context, 4, GridLayoutManager.HORIZONTAL, false)
        binding.listQuickPicks.layoutManager = layoutManager
        binding.listQuickPicks.adapter = quickPicksAdapter
        binding.progressQuickPicks.visibility = View.VISIBLE

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.listQuickPicks)

        songViewModel.quickPicksList.observe(viewLifecycleOwner, Observer {
            val quickPicksList = it.take(24).toMutableList()
            quickPicksAdapter.updateList(quickPicksList)

            binding.listQuickPicks.visibility = View.VISIBLE
            binding.progressQuickPicks.visibility = View.GONE
        })
    }

    private fun initLibrary() {
        val libraryAdapter = LibraryHomeAdapter(mutableListOf())
        binding.listLibrary.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.listLibrary.adapter = libraryAdapter
        binding.progressLibrary.visibility = View.VISIBLE

        libraryViewModel.libraryHomeList.observe(viewLifecycleOwner, Observer {
            libraryAdapter.updateList(it)

            binding.listLibrary.visibility = View.VISIBLE
            binding.progressLibrary.visibility = View.GONE
        })
    }

    private fun initTrending() {
        val trendingAdapter = TrendingHomeAdapter(mutableListOf())
        val layoutManager = GridLayoutManager(context, 4, GridLayoutManager.HORIZONTAL, false)
        binding.listTrending.layoutManager = layoutManager
        binding.listTrending.adapter = trendingAdapter
        binding.progressTrending.visibility = View.VISIBLE

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.listTrending)

        songViewModel.trendingList.observe(viewLifecycleOwner, Observer { songList ->
            val sortedList = songList.sortedByDescending { it.view }
            val limitedList = sortedList.take(24).toMutableList()
            trendingAdapter.updateList(limitedList)

            binding.listTrending.visibility = View.VISIBLE
            binding.progressTrending.visibility = View.GONE
        })
    }

    private fun initFollow() {
        val channelAdapter = FollowSuggestAdapter(mutableListOf())
        binding.listFollow.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.listFollow.adapter = channelAdapter
        binding.progressFollow.visibility = View.VISIBLE

        channelViewModel.channelList.observe(viewLifecycleOwner, Observer {
            channelAdapter.updateList(it)

            binding.listFollow.visibility = View.VISIBLE
            binding.progressFollow.visibility = View.GONE
        })
    }

    private fun initMusicGenre() {
        val musicGenreAdapter = MusicGenreAdapter(mutableListOf())
        binding.listMusicGenre.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.listMusicGenre.adapter = musicGenreAdapter
        binding.progressMusicGenre.visibility = View.VISIBLE

        genreViewModel.musicGenreList.observe(viewLifecycleOwner, Observer {
            musicGenreAdapter.updateList(it)

            binding.listMusicGenre.visibility = View.VISIBLE
            binding.progressMusicGenre.visibility = View.GONE
        })
    }
}
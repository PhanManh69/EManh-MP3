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
import com.emanh.mp3.viewModel.SongViewModel

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val songViewModel: SongViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initClick()
        initListenAgain()
        initQuickPicks()

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
            quickPicksAdapter.updateList(it)

            binding.listQuickPicks.visibility = View.VISIBLE
            binding.progressQuickPicks.visibility = View.GONE
        })
    }
}
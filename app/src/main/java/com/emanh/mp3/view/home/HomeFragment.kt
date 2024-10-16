package com.emanh.mp3.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanh.mp3.databinding.FragmentHomeBinding
import com.emanh.mp3.helper.BaseFragment
import com.emanh.mp3.view.MainActivity
import com.emanh.mp3.viewModel.ListenAgainViewModel

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val listenAgainViewModel: ListenAgainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initClick()
        initListenAgain()

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

        listenAgainViewModel.listenAgainList.observe(viewLifecycleOwner, Observer {
            val limitedList = it.take(10).toMutableList()
            listenAgainAdapter.updateList(limitedList)
        })
    }
}
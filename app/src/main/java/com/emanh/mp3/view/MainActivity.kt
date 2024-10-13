package com.emanh.mp3.view

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.emanh.mp3.R
import com.emanh.mp3.databinding.ActivityMainBinding
import com.emanh.mp3.helper.BaseActivity
import com.emanh.mp3.view.home.HomeFragment
import com.emanh.mp3.view.library.LibraryFragment
import com.emanh.mp3.view.search.SearchFragment
import com.emanh.mp3.view.setting.SettingFragment

class MainActivity : BaseActivity() {
    enum class Tab {
        HOME, SEARCH, LIBRARY, SETTING
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClick()
    }

    override fun initClick() {
        val txtButtonHome = binding.txtButtonHome
        val txtButtonSearch = binding.txtButtonSearch
        val txtButtonLibrary = binding.txtButtonLibrary
        val txtButtonSetting = binding.txtButtonSetting
        val tabButtons = mapOf(
            Tab.HOME to txtButtonHome,
            Tab.SEARCH to txtButtonSearch,
            Tab.LIBRARY to txtButtonLibrary,
            Tab.SETTING to txtButtonSetting,
        )

        replaceFragment(HomeFragment())
        selectTab(Tab.HOME, tabButtons)

        txtButtonHome.setOnClickListener {
            selectTab(Tab.HOME, tabButtons)
            replaceFragment(HomeFragment())
        }

        txtButtonSearch.setOnClickListener {
            selectTab(Tab.SEARCH, tabButtons)
            replaceFragment(SearchFragment())
        }

        txtButtonLibrary.setOnClickListener {
            selectTab(Tab.LIBRARY, tabButtons)
            replaceFragment(LibraryFragment())
        }

        txtButtonSetting.setOnClickListener {
            selectTab(Tab.SETTING, tabButtons)
            replaceFragment(SettingFragment())
        }
    }

    @SuppressLint("ResourceAsColor", "UseCompatTextViewDrawableApis")
    private fun selectTab(selectedTab: Tab, tabButtons: Map<Tab, TextView>) {
        for ((tab, button) in tabButtons) {
            val isSelected = tab == selectedTab
            val color = if (isSelected) {
                ContextCompat.getColor(this, R.color.blue)
            } else {
                ContextCompat.getColor(this, R.color.grey)
            }

            button.setTextColor(color)
            button.setCompoundDrawableTintList(ColorStateList.valueOf(color))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.layoutMain, fragment)
        fragmentTransaction.commit()
    }
}
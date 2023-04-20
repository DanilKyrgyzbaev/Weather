package com.art.studio.weather.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private var fragments = emptyList<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun submitList(newList: List<Fragment>) {
        fragments = newList
        notifyDataSetChanged()
    }
}

package com.art.studio.weather.ui.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.art.studio.weather.R

class AddCityFragment : Fragment() {

    companion object {
        fun newInstance() = AddCityFragment()
    }

    private lateinit var viewModel: AddCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
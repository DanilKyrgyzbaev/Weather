package com.art.studio.weather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.art.studio.weather.BuildConfig
import com.art.studio.weather.R
import com.art.studio.weather.databinding.FragmentHomeBinding
import com.art.studio.weather.ui.MainViewModel
import com.art.studio.weather.ui.adapter.MyPagerAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var customMenu: PopupMenu
    private lateinit var lSwipeDetector: GestureDetectorCompat
    private var language = "en"
    private lateinit var fragmentAdapter: MyPagerAdapter
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController



    companion object {
        private const val SWIPE_MIN_DISTANCE = 130
        private const val SWIPE_MAX_DISTANCE = 300
        private const val SWIPE_MIN_VELOCITY = 200
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        fragmentAdapter = MyPagerAdapter(requireActivity())

        binding.viewPager.adapter = fragmentAdapter
        binding.viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

//        viewModel.fragmentList.observe(viewLifecycleOwner) { fragmentList ->
//            fragmentList?.let { fragmentAdapter.submitList(it) }
//        }

        val navController = activity?.findNavController(R.id.nav_host_fragment)

        binding.addCity.setOnClickListener {
            navController?.navigate(R.id.action_homeFragment_to_addCityFragment)
        }

        lSwipeDetector = GestureDetectorCompat(requireContext(), MyGestureListener())
        binding.homeLayout.setOnTouchListener { _, event -> lSwipeDetector.onTouchEvent(event) }
        optionMenu()

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun optionMenu(){
        customMenu = PopupMenu(requireActivity(), binding.moreVert)
        customMenu.inflate(R.menu.menu)
        customMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.setting -> {
                    navController.navigate(R.id.action_homeFragment_to_settingFragment)
                    true
                }
                R.id.share -> {
                    Toast.makeText(context,"Share Selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        binding.moreVert.setOnClickListener {
            customMenu.show()
        }
    }


    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (Math.abs(e1.y - e2.y) > SWIPE_MAX_DISTANCE) return false
            if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY) {
//                getLongitudeLatitude()
                Log.e("MotionEvent","MotionEvent")
            }
            return false
        }
    }
}
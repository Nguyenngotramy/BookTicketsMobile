package com.example.bookticketsmobile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.bookticketsmobile.databinding.FilmlistItemBinding
import com.example.bookticketsmobile.databinding.FragmentHomeBinding
import kotlin.math.abs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var binding: FragmentHomeBinding
private lateinit var binding2: FilmlistItemBinding
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding2 = FilmlistItemBinding.inflate(layoutInflater)
//        //khai báo list phim
        var list = mutableListOf<FilmDataHome>()
        list.add(FilmDataHome(R.drawable.latmat7poster, "Lật Mặt 7: Một Điều Ước"))
        list.add(FilmDataHome(R.drawable.hanhtinhkhiposter, "Hành Tinh Khỉ: Vương Quốc Mới"))
        list.add(FilmDataHome(R.drawable.doremonposter, "Doraemon: Nobita và bản giao hưởng Địa cầu"))
        list.add(FilmDataHome(R.drawable.thanhxuanposter, "Thanh xuân 18x2: Lữ trình hướng về em"))
//
//        val customGV = GridFilmList(requireActivity(), list)
        val adapter = FilmViewRecycle(list)
        binding.filmList.adapter = adapter
        binding.filmList.layoutManager = GridLayoutManager(requireActivity(), 2, GridLayoutManager.HORIZONTAL,false)
//        binding.filmList.getChildItemId(R.layout.filmlist_item.bookBtn)
        binding2.bookBtn.setOnClickListener {
            val details = Intent(requireActivity(), DetailOfFilm::class.java)
            startActivity(details)
        }
        return binding.root

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}
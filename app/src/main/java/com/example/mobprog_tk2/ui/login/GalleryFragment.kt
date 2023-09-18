package com.example.mobprog_tk2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobprog_tk2.ImageAdapter
import com.example.mobprog_tk2.R


class GalleryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageList: List<Int>
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recycledViewPool = RecyclerView.RecycledViewPool()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setRecycledViewPool(recycledViewPool)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // List of drawable
        imageList = listOf(
            R.drawable.welcome_heart_green,
            R.drawable._0,
            R.drawable.img_20200107_065538,
            R.drawable.img_20200107_065539,
            R.drawable.img_20210408_122056,
            R.drawable.dc_sticker_0_kobo,
            R.drawable.welcome_heart_green,
            R.drawable._0,
            R.drawable.img_20200107_065538,
            R.drawable.img_20200107_065539,
            R.drawable.img_20210408_122056,
            R.drawable.dc_sticker_0_kobo,
            R.drawable.welcome_heart_green,
            R.drawable._0,
            R.drawable.img_20200107_065538,
            R.drawable.img_20200107_065539,
            R.drawable.img_20210408_122056,
            R.drawable.dc_sticker_0_kobo,
            R.drawable.welcome_heart_green,
            R.drawable._0,
            R.drawable.img_20200107_065538,
            R.drawable.img_20200107_065539,
            R.drawable.img_20210408_122056,
            R.drawable.dc_sticker_0_kobo,
            R.drawable.welcome_heart_green,
            R.drawable._0,
            R.drawable.img_20200107_065538,
            R.drawable.img_20200107_065539,
            R.drawable.img_20210408_122056,
            R.drawable.dc_sticker_0_kobo,
            R.drawable.welcome_heart_green,
            R.drawable._0,
            R.drawable.img_20200107_065538,
            R.drawable.img_20200107_065539,
            R.drawable.img_20210408_122056,
            R.drawable.dc_sticker_0_kobo,
        )
        imageAdapter = ImageAdapter(imageList)

        recyclerView.adapter = imageAdapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter = null
        imageList = emptyList()
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView.adapter = null
        imageList = emptyList()
    }

    override fun onDetach() {
        super.onDetach()
        recyclerView.adapter = null
        imageList = emptyList()
    }
}
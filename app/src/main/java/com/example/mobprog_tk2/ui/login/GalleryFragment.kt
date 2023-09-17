package com.example.mobprog_tk2.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobprog_tk2.ImageAdapter
import com.example.mobprog_tk2.MainActivity
import com.example.mobprog_tk2.R
import com.example.mobprog_tk2.data.LoginDataSource
import com.example.mobprog_tk2.data.LoginRepository


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

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Set the layout manager
        // List of drawable
        imageList = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.welcome_heart_green,
            R.drawable.ic_launcher_background
        )
        imageAdapter = ImageAdapter(imageList)
        recyclerView.adapter = imageAdapter

        return view
    }
}
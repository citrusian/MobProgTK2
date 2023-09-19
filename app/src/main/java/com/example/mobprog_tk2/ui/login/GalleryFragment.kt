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
import com.squareup.picasso.Picasso


class GalleryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageList: List<String>
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Picasso.get()
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recycledViewPool = RecyclerView.RecycledViewPool()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setRecycledViewPool(recycledViewPool)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // List of drawable
        imageList = listOf(
            "https://i.imgur.com/DvpvklR.png",
            "https://www.google.com/logos/doodles/2015/googles-new-logo-5078286822539264.3-hp2x.gif",
            "https://goo.gl/gEgYUd"
        )
        imageAdapter = ImageAdapter(imageList)

        recyclerView.adapter = imageAdapter

        return view
    }
}
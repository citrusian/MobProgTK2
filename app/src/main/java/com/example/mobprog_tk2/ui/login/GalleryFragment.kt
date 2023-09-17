package com.example.mobprog_tk2.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mobprog_tk2.MainActivity
import com.example.mobprog_tk2.R
import com.example.mobprog_tk2.data.LoginDataSource
import com.example.mobprog_tk2.data.LoginRepository


class GalleryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageList: List<Int>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = requireActivity() as MainActivity
        val logoutButton = mainActivity.findViewById<Button>(R.id.LogoutButton)
        logoutButton.setOnClickListener {

            // Redirect to MainActivity to Simulate Logout
//            LoginRepository.logout()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }
}
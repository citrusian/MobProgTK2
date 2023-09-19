package com.example.mobprog_tk2

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageAdapter(private val imageList: List<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = imageList[position]
//        holder.imageView.setImageResource(image)
//        Picasso.get()
//            .load(image)
//            .into(holder.imageView, object : Callback {
//                override fun onSuccess() {
//                    // Image loaded successfully
//                }
//
//                override fun onError(e: Exception?) {
//                    // Handle the error here, e.g., log it
//                    Log.e("Picasso", "Error loading image: ${e?.message}")
//                }
//            })
//        loadImageWithRetry(image, holder.imageView)
//        loadImageWithRetry("https://www.google.com/logos/doodles/2015/googles-new-logo-5078286822539264.3-hp2x.gif", holder.imageView)

        Glide.with(holder.imageView.context).load("https://goo.gl/gEgYUd").into(holder.imageView);
//        Glide.with(holder.imageView.context)
//            .load(image)
//            .into(holder.imageView)
    }


    /**
     Picasso throwing error 504, so the connection not working.
     trying https://stackoverflow.com/questions/58430498/error-http-504-when-load-image-form-url-with-picasso-android-library
     didn't fix it, and Glide also throwing error -1
     **/


    fun loadImageWithRetry(url: String, imageView: ImageView, retryCount: Int = 3) {
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(url)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    Log.e("DEBUG", "Image Loaded")
                }

                override fun onError(e: Exception?) {
                    if (retryCount > 0) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadImageWithRetry(url, imageView, retryCount - 1)
                        }, 5000) //
                    } else {
                        // Handle the error here, e.g., log it or display an error message
                        Log.e("Picasso", "Error Message: ${e?.message}")
                    }
                }
            })
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun clear() {
            imageView.setImageBitmap(null)
        }
    }
}

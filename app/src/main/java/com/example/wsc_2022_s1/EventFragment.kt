package com.example.wsc_2022_s1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class EventFragment : Fragment(R.layout.fragment_event) {

    private fun main(view: View) {
        view.findViewById<TextView>(R.id.title)?.text = arguments?.getString("title")
        view.findViewById<TextView>(R.id.viewCount)?.text = arguments?.getInt("viewCount").toString()

        Picasso.get().load(arguments?.getStringArray("images")?.get(0)).into(view.findViewById<ImageView>(R.id.image1))
        Picasso.get().load(arguments?.getStringArray("images")?.get(1)).into(view.findViewById<ImageView>(R.id.image2))
        Picasso.get().load(arguments?.getStringArray("images")?.get(2)).into(view.findViewById<ImageView>(R.id.image3))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main(view)
    }

}
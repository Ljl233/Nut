package com.example.nut.ui.mine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.NavHostFragment
import com.example.nut.R

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        val navIcon = root.findViewById<ImageView>(R.id.detail_nav)
        navIcon.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
        return root
    }

}
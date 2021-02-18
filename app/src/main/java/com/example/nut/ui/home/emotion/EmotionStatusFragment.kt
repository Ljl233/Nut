package com.example.nut.ui.home.emotion

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.example.nut.R

class EmotionStatusFragment : Fragment() {

    lateinit var mTvStatus: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_emotion_status, container, false)

        mTvStatus = root.findViewById(R.id.status_status)
        val toolbar = root.findViewById<Toolbar>(R.id.status_toolbar)
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val background = mTvStatus.background
            if (background is GradientDrawable) {
                Toast.makeText(this.context, "show", Toast.LENGTH_SHORT).show()
                (background as GradientDrawable).setColor(Color.parseColor("#03DAC5"))
            }
        }
        return root
    }
}
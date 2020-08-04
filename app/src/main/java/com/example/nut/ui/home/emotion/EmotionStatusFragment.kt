package com.example.nut.ui.home.emotion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nut.R

class EmotionStatusFragment : Fragment() {

    lateinit var mTvStatus: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_emotion_status, container, false)

        mTvStatus = root.findViewById(R.id.status_status)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
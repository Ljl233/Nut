package com.example.nut.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nut.R
import com.facebook.drawee.view.SimpleDraweeView

class MineFragment : Fragment() {

    lateinit var mIvAvatar: SimpleDraweeView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = LayoutInflater.from(container!!.context).inflate(R.layout.fragment_mine, container, false)


        mIvAvatar = root.findViewById(R.id.mine_avatar)


        return root
    }
}
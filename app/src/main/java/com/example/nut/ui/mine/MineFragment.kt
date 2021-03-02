package com.example.nut.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.nut.R
import com.facebook.drawee.view.SimpleDraweeView

class MineFragment : Fragment() {

    lateinit var mIvAvatar: SimpleDraweeView
    lateinit var mLayoutInfo: ConstraintLayout

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = LayoutInflater.from(container!!.context).inflate(R.layout.fragment_mine, container, false)

        mIvAvatar = root.findViewById(R.id.mine_avatar)
        mIvAvatar.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.info_dest)
        }
        mLayoutInfo = root.findViewById(R.id.mine_info)
        mLayoutInfo.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.info_dest)
        }

        return root
    }
}
package com.example.nut.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.nut.R
import com.example.nut.database.User
import com.example.nut.database.getDatabase
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MineFragment : Fragment() {

    lateinit var mIvAvatar: SimpleDraweeView
    lateinit var mLayoutInfo: ConstraintLayout
    lateinit var mTvName: TextView
    lateinit var mTvSlogan: TextView
    lateinit var mTvCoin: TextView

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

        mTvName = root.findViewById(R.id.mine_name)
        mTvSlogan = root.findViewById(R.id.mine_slogan)
        mTvCoin = root.findViewById(R.id.mine_coin)

        initView()
        return root
    }

    private fun initView() {
        getDatabase(requireContext()).userDao.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<User>> {
                    override fun onSuccess(t: List<User>) {
                        if (t.isEmpty()) {
                            return
                        }
                        val user = t[0]
                        if (user.avatar != null) {
                            mIvAvatar.setImageURI(user.avatar)
                        }
                        mTvName.text = user.name
                        if (user.slogan != null) {
                            mTvSlogan.text = user.slogan
                        }
                        if (user.coin != null) {
                            mTvCoin.text = user.coin.toString()
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }
}
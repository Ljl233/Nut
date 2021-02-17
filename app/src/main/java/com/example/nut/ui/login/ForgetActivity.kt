package com.example.nut.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nut.R

class ForgetActivity : AppCompatActivity() {
    lateinit var mEdAccount: EditText
    lateinit var mEtAuthCode: EditText
    lateinit var mEtPsw: EditText
    lateinit var mEtPswCheck: EditText
    lateinit var mBtLogup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        mEdAccount = findViewById(R.id.forget_account)
        mEtAuthCode = findViewById(R.id.forget_auth_code)
        mEtPsw = findViewById(R.id.forget_psw)
        mEtPswCheck = findViewById(R.id.forget_check)
        mBtLogup = findViewById(R.id.forget_button)

        initView()
    }

    private fun initView() {
        mBtLogup.setOnClickListener { v ->
            if (postAccountPsw()) {
                Toast.makeText(this, "找回密码成功", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "找回密码失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postAccountPsw(): Boolean {
        return true
    }
}

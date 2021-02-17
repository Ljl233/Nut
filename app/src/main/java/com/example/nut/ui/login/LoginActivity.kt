package com.example.nut.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nut.R
import com.example.nut.ui.maincontrol.MainActivity

class LoginActivity : Activity() {
    lateinit var mButtonLogin: Button
    lateinit var mEtAccount: EditText
    lateinit var mEtPassword: EditText

    lateinit var mButtonForget: Button
    lateinit var mButtonLogup: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mButtonLogin = findViewById(R.id.login_bt)
        mEtAccount = findViewById(R.id.login_account)
        mEtPassword = findViewById(R.id.login_psw)
        mButtonForget = findViewById(R.id.login_forget)
        mButtonLogup = findViewById(R.id.login_logup)

        initView()
    }


    private fun initView() {
        mButtonLogup.setOnClickListener { v ->
            val intent = Intent(this, LogupActivity::class.java)
            startActivity(intent)
        }

        mButtonForget.setOnClickListener { v ->
            val intent = Intent(this, ForgetActivity::class.java)
            startActivity(intent)
        }

        mButtonLogin.setOnClickListener { v ->
            if (checkAccountPsw()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAccountPsw(): Boolean {
        if (mEtAccount.text.isBlank() || mEtPassword.text.isBlank()) return false

        TODO("检查账号密码")

        return true
    }
}
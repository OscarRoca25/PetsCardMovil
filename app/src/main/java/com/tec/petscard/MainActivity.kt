package com.tec.petscard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import com.tec.petscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        val animationScale = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        binding.imgLogo.startAnimation(animationScale)

        checkUser()

    }

    private fun checkUser(){
        val thread = Thread {
            Thread.sleep(1200)
            val user = firebaseAuth!!.currentUser
            val isEmailVerified = if (user != null)firebaseAuth!!.currentUser!!.isEmailVerified
                                else false
            val intent = if (user == null || !isEmailVerified) Intent(this, LoginActivity::class.java)
            else Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        thread.start()
    }



}
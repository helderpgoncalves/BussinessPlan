package com.bitdev.bussinessplan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bitdev.bussinessplan.activities.LoginActivity
import com.bitdev.bussinessplan.activities.formulary.FormularyActivity
import com.bitdev.bussinessplan.utils.Constants
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {

    private lateinit var welcomeTextView: TextView
    private lateinit var formularyButton: Button
    private lateinit var logoutButton: Button

    private var userID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase = Firebase.initialize(this)
        welcomeTextView = findViewById(R.id.welcome_message)
        formularyButton = findViewById(R.id.goto_form_btn)
        logoutButton = findViewById(R.id.logout_btn)


        userID = Constants.getPreferenceUserID(applicationContext)


        formularyButton.setOnClickListener {
            gotoFormularyActivity()
        }

        logoutButton.setOnClickListener {
            logout()
        }

        if(userID == null){
            gotoLoginActivity()
            return
        }

        welcomeTextView.text = "Hi $userID!"
    }

    override fun onResume() {
        super.onResume()

        userID = Constants.getPreferenceUserID(applicationContext)

        welcomeTextView.text = "Hi $userID!"


    }

    private fun logout(){
        Constants.setPreferenceUserID(null, applicationContext)
        gotoLoginActivity()
    }

    private fun gotoLoginActivity(){
        startActivity(Intent(applicationContext,LoginActivity::class.java))
    }

    private fun gotoFormularyActivity(){
        if(userID == null) return
        startActivity(Intent(applicationContext, FormularyActivity::class.java))
    }
}
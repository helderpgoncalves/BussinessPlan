package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bitdev.bussinessplan.R
import com.bitdev.bussinessplan.databinding.ActivityFormularyBinding
import com.bitdev.bussinessplan.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FormularyActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFormularyBinding

    private lateinit var businessData: MutableMap<String,String>

    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormularyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        db = Firebase.firestore

        val navController = findNavController(R.id.nav_host_fragment_content_formulary)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        businessData = mutableMapOf()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_formulary)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun setData(key: String, value: String){
        businessData[key] = value

        Log.d("BITDEBUG","$key: $value" )
    }

    fun getBusinessType(): String? {
        return businessData["type"]
    }

    fun finir() {
        val userID = Constants.getPreferenceUserID(applicationContext)

        if(userID == null) {
            finish()
            return
        }

       db.collection("users").document(userID).update("business",businessData).addOnSuccessListener {
           Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
           finish()
       }
    }
}
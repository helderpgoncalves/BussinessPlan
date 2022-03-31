package com.bitdev.bussinessplan.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.bitdev.bussinessplan.R
import com.bitdev.bussinessplan.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = Firebase.firestore

        usernameInput = findViewById<EditText>(R.id.login_username)
        passwordInput = findViewById<EditText>(R.id.login_password)
        submitButton = findViewById<Button>(R.id.login_submit)

        updateButton()

        usernameInput.doAfterTextChanged {
            updateButton()
        }

        passwordInput.doAfterTextChanged {
            updateButton()
        }

        submitButton.setOnClickListener {
            val username = usernameInput.text
            val password = passwordInput.text

            Toast.makeText(applicationContext, "Connection to server.. ", Toast.LENGTH_SHORT).show()

            db.collection("users")
                .whereEqualTo("name", "$username")
                .get()
                .addOnSuccessListener { documents ->

                    if(documents.size() == 0){
                        Toast.makeText(applicationContext, resources.getString(R.string.username_not_found), Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }

                    for(document in documents){
                        if(document.data["password"].toString() == password.toString()){
                            Constants.setPreferenceUserID(username.toString(), applicationContext)

                            finish()
                            return@addOnSuccessListener
                        }
                    }

                    Toast.makeText(applicationContext, resources.getString(R.string.wrong_password), Toast.LENGTH_SHORT).show()


                }
                .addOnFailureListener {
                    Log.d("BITDEBUG", it.message.toString())
                }
        }
    }

    private fun updateButton(){
        if(usernameInput.text.isNotEmpty() && passwordInput.text.isNotEmpty()){
            submitButton.isEnabled = true
            submitButton.alpha = 1f
            submitButton.isClickable = true
        }else{
            submitButton.isEnabled = false
            submitButton.alpha = .5f
            submitButton.isClickable = false
        }
    }
}
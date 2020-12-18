package org.techtown.tiny_weather

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.actiivity_login.*

class Login : AppCompatActivity() {

    var auth : FirebaseAuth?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivity_login)

        auth = FirebaseAuth.getInstance()

        val intent = Intent(this, LodingActivity::class.java)
        startActivity(intent)

        join_btn.setOnClickListener {
            login()
        }
    }

    fun login(){
        auth?.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        //Creating a user account
                        goHome(task.result?.user)
                    } else{
                        signin()
                    }
                }
    }

    fun signin(){
        auth?.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        goHome(task.result?.user)
                    } else{
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
    }

    fun goHome(user: FirebaseUser?){
        if (user != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
package org.techtown.tiny_weather

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.actiivity_login.*

class LoginActivity : AppCompatActivity() {

    var auth : FirebaseAuth?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivity_login)

        auth = FirebaseAuth.getInstance()

        val intent = Intent(this, LodingActivity::class.java)
        startActivity(intent)

        join_btn.setOnClickListener {
            if(email.text.toString() != "" && password.text.length > 7){
                login()
            }else{
                Toast.makeText(this, "빈칸을 채우거나 비밀번호를 8자 이상 작성하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(){

        auth?.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        //Creating a user account
                        var builder = AlertDialog.Builder(this)
                        builder.setTitle("회원가입")
                        builder.setMessage("회원가입하시겠습니까?")
                        builder.setIcon(R.mipmap.ic_launcher_logo)

                        var listener = DialogInterface.OnClickListener{
                            _,p1 ->
                            when(p1){
                                DialogInterface.BUTTON_POSITIVE->
                                    goHome(task.result?.user)
                                DialogInterface.BUTTON_NEGATIVE ->
                                    Toast.makeText(this, "미 가입시 앱 이용 불가", Toast.LENGTH_SHORT).show()
                            }
                        }

                        builder.setPositiveButton("예",listener)
                        builder.setNegativeButton("아니요",listener)

                        builder.show();
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
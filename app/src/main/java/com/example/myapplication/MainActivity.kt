package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // save to firebase
        //Mahmoud
        btnSave.setOnClickListener {
             connectTOFireBase()
        }
    }

    fun connectTOFireBase() {

        val name = txtName.text.toString()
        val age = txtAge.text.toString()
        val id = txtId.text.toString()

        if (name.isNotEmpty() && age.isNotEmpty() && id.isNotEmpty()) {

            val user = hashMapOf(
                "name" to name,
                "age" to age,
                "id" to id,
            )

            db.collection("userInfo")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "uploaded SuccessFully", Toast.LENGTH_SHORT).show()
                }

                .addOnFailureListener {
                    Toast.makeText(this, "Failed to upload", Toast.LENGTH_SHORT).show()
                }
        }else{
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }
}

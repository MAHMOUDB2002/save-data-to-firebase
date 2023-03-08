package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_real_time_database.*

class RealTimeDatabaseActivity : AppCompatActivity() {
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_time_database)

        val database = Firebase.database
        val myRef = database.getReference()

        btnSave.setOnClickListener {

            val name = txtname.text.toString()
            val id = txtId.text.toString()
            val age = txtAge.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty() && age.isNotEmpty()) {

                val persons = hashMapOf(
                    "name" to name,
                    "id" to id,
                    "age" to age,

                    )
                // myRef.setValue(persons)
                // بيضيف بناء علي الاي دي و ما حطيت اي دي حيعمل تعديل على الداتا القديمة
                // myRef.child("Person").child("id").setValue(persons)
                myRef.child("Person").child("$count").setValue(persons)
                count++
                Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()

            }

        }

        btnGetData.setOnClickListener {

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue()
                    txtData.text = value.toString()
                    Toast.makeText(applicationContext, "success Added", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Failed to Add", Toast.LENGTH_SHORT).show()
                }

            })

        }

    }
}
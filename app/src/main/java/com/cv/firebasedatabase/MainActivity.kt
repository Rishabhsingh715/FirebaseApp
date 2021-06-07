package com.cv.firebasedatabase

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
     var notes: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")



        btn1.setOnClickListener {
            val note = ed1.text.toString()

            myRef.child("A").push().setValue(note)
           // myRef.child("B").push().setValue(note)

        }


        myRef.child("A").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                val data = snapshot.getValue(String::class.java)
                if (data != null) {
                    notes.add(data)
                }
                lv1.adapter = ArrayAdapter<String>(
                    this@MainActivity,
                    R.layout.items,
                    R.id.tv2,
                    notes
                )






            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}
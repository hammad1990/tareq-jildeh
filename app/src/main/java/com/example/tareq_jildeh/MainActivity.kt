package com.example.tareq_jildeh

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var btn :Button
    lateinit var etx1 :EditText
    lateinit var txt1 :TextView
    lateinit var txt2 :TextView
    lateinit var txt3 :TextView
    lateinit var txt4 :TextView
    lateinit var txt5 :TextView
    lateinit var txt6 :TextView
    lateinit var txttest :TextView
    var k :Double = 0.0
    lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        btn= findViewById(R.id.btn)
        etx1= findViewById(R.id.etx1)
        txt1= findViewById(R.id.txt1)
        txt2= findViewById(R.id.txt2)
        txt3= findViewById(R.id.txt3)
        txt4= findViewById(R.id.txt4)
        txt5= findViewById(R.id.txt5)
        txt6= findViewById(R.id.txt6)
        txttest= findViewById(R.id.txttest)


        etx1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                btn.isEnabled=true
               // L1 =etx1.text.toString().toDouble()

               /* if (TextUtils.isEmpty(etx1.text.toString()) ) {
                    Toast.makeText(this@MainActivity,"there is an empty reading",Toast.LENGTH_SHORT).show()
                }else{

                    //L1 =etx1.text.toString().toDouble()
                    //var L2: Double =etx2.text.toString().toDouble()
                    //database.child("k").setValue(data(L1,L2))-----adding key
                    // database.child("l1").setValue(0)
                    // database.child("l2").setValue(0)


                }*/

            }
        })
        btn.setOnClickListener(){
            if (TextUtils.isEmpty(etx1.text.toString()) ) {
                Toast.makeText(this@MainActivity,"there is an empty reading",Toast.LENGTH_SHORT).show()
            }else{
                k =etx1.text.toString().toDouble()
                database.child("test").setValue(data (k))
            }
        }

        var getdata=object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var A1=snapshot.child("L1").getValue()
                var A2=snapshot.child("L2").getValue()
                var A3=snapshot.child("L3").getValue()
                var v1=snapshot.child("V1").getValue()
                var v2=snapshot.child("V2").getValue()
                var v3=snapshot.child("V3").getValue()
                var test=snapshot.child("test").getValue()
                txt1.setText(A1.toString())
                txt2.setText(A2.toString())
                txt3.setText(A3.toString())
                txt4.setText(v1.toString())
                txt5.setText(v2.toString())
                txt6.setText(v3.toString())
                txttest.setText(test.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }


        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }
}












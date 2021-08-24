package com.myweb.lab7dialogrv


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.myweb.lab7dialogrv.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
     private lateinit var binding : ActivityMainBinding
     var studentList = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root )

        studentData()
        binding.recyclerView.adapter = StudentsAdapter(this.studentList, applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecor)
    }

    fun studentData() {
        //prepare student data for testing
        studentList.add(Student("621234567-8", "Alice", 20))
        studentList.add(Student("629876543-2", "Bob", 21))
    }

    fun addStudent(v: View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.setView(mDialogView)

        ///Save Button
        myBuilder.setPositiveButton("Save" ) { dialog, which ->
            val id = mDialogView.findViewById(R.id.edt_id) as TextInputEditText
            val name = mDialogView.findViewById(R.id.edt_name) as TextInputEditText
            val age = mDialogView.findViewById(R.id.edt_age) as TextInputEditText

            studentList.add(
            Student(id.text.toString(),name.text.toString(),age.text.toString().toInt()))

            binding.recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(
                    applicationContext,
                    "The student is added successfully",
                    Toast.LENGTH_LONG
            ).show()
        }
        ///Cancel Button
        myBuilder.setNegativeButton("Cancel",) { dialog, which ->
            dialog.dismiss()
        }
        myBuilder.show()
    }
}

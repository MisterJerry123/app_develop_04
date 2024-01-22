package com.example.alomtest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.alomtest.databinding.ExerciseAddCustomListBinding
import com.example.alomtest.databinding.FragmentExerciseMainBinding
import com.example.alomtest.login.account2

private lateinit var binding: ExerciseAddCustomListBinding


class exercise_add_custom_list : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ExerciseAddCustomListBinding.inflate(layoutInflater)

        val backicon = binding.cancelicon

//        backicon.setOnClickListener {
//            val intent = Intent(this@exercise_add_custom_list,exercise_main_copy::class.java)
//
//        }
    }


//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = parentFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frame_layout,fragment)
//        fragmentTransaction.commit()
//        println("success")
//
//    }
    companion object {

    }
}
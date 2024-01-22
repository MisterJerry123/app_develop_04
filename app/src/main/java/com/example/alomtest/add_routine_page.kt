package com.example.alomtest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.databinding.AddRoutinePageBinding

import com.example.alomtest.databinding.FragmentExerciseMainBinding
import com.example.alomtest.login.account2


class add_routine_page : AppCompatActivity() {

    private lateinit var binding: AddRoutinePageBinding
    lateinit var exercise_recycler_view : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= AddRoutinePageBinding.inflate(layoutInflater)

        val addbtn = binding.exerciseAddBtn
        val backicon=binding.cancelicon

        addbtn.setOnClickListener {
            val intent = Intent(this@add_routine_page,add_routine_page::class.java)
            //val intent = Intent(this@account, account2::class.java)
            startActivity(intent)
            finish()


        }
//        backicon.setOnClickListener {
//            replaceFragment(exercise_main())
//        }


        //backicon은 나중에 구현




    //View.findViewById(R.id.exercise_view)


    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        //super.onViewCreated(view, savedInstanceState)
//        super.onCreate(savedInstanceState)
//
//
//
//
//        val customList = arrayListOf(
//            exerciseData("대흉근 발달, 3대 운동","01 바벨 벤치 프레스")
//
//            )
//
//        println("routine page 진입1")
//        exercise_recycler_view = view.findViewById(R.id.exercise_view)
//        println("routine page 진입2")
//        exercise_recycler_view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        println("routine page 진입3")
//        exercise_recycler_view.setHasFixedSize(true)
//        println("routine page 진입4")
//
//        exercise_recycler_view.adapter = exercise_list_adpater(customList)
//        println("routine page 진입5")
//    }
//    private var _binding: AddRoutinePageBinding? = null
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//
//        _binding = AddRoutinePageBinding.inflate(inflater,container,false)
//        return binding.root
//        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
//    }

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
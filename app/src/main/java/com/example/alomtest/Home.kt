package com.example.alomtest

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextClock
import com.example.alomtest.databinding.FragmentHomeBinding
import com.example.alomtest.databinding.FragmentSettingsBinding
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout


import java.time.LocalDate
import java.time.LocalDateTime

class Home : Fragment() {



    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val mSeekBar = binding.seekBar
        //val seekbar_Frame = binding.seekbarFrame

        //11/14 추가
        val mSeekBar = binding.seekBar
        val frameLayout = binding.seekbarFrame

        // SeekBar를 최대 크기로 설정
        val layoutParams = FrameLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        mSeekBar.layoutParams = layoutParams




//        //이미지 겹칠 때 앞서게 설정
//        //val image1=binding.imageView4
//        //val image2=binding.imageView5
//        //image2.bringToFront();
//
//        mSeekBar.isEnabled = false //
//        // Set the min, max and current
//        // values to the SeekBar
//        //tes
//        var mMin = 0
//        var mMax = 30
//        var mCurrent = 20
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mSeekBar.min = mMin
//            mSeekBar.max = mMax
//        }
//        //bmi계산 후 seekbar의 thumb위치 설정
//        var weight:Double
//        var height:Double
//
//
//        weight=80.0
//        height=1.74
//
//        var bmi:Double
//
//
//
//        bmi=weight/(height*height)
//
//
//        println(bmi)
//
//        mSeekBar.thumbOffset= (bmi).toInt()
//
//
//
//
//        // Set the current to progress
//        // and display in the TextView
//        mSeekBar.progress = mCurrent

        // On Change Listener to change
        // current values as drag
//        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//                mCurrent = p1
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {}
//            override fun onStopTrackingTouch(p0: SeekBar?) {}
//        })
        //edittext를 임의수정 못하게 막은 코드
        val saying=binding.editTextText
        saying.keyListener = null
    }





}
package com.example.alomtest

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextClock
import com.example.alomtest.databinding.FragmentHomeBinding
import com.example.alomtest.databinding.FragmentSettingsBinding

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

        val mSeekBar = binding.seekBar

        // Set the min, max and current
        // values to the SeekBar
        var mMin = 0
        var mMax = 100
        var mCurrent = 20
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mSeekBar.min = mMin
            mSeekBar.max = mMax
        }

        // Set the current to progress
        // and display in the TextView
        mSeekBar.progress = mCurrent

        // On Change Listener to change
        // current values as drag
        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                mCurrent = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }




}
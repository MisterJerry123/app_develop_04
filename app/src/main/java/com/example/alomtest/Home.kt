package com.example.alomtest

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alomtest.databinding.FragmentHomeBinding
import kotlin.math.roundToInt

class Home : Fragment() {



    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mseekbar = binding.bmigauge
        //val seekbar_veryfat=binding.veryfat

        //글자를 seekbar로 배치할 예정
        //val seekbar_veryfat=binding.veryfat
        //val seekbar_normal=binding.normal
        //val seekbar_lowweight=binding.lowweight



        //디바이스의 width와 height를 가져오는 코드
        fun getScreenWidth(context: Home): Int {
            val displayMetrics = DisplayMetrics()

            var phonewidth=Resources.getSystem().displayMetrics.widthPixels
            var phoneheight=Resources.getSystem().displayMetrics.heightPixels

            return phonewidth
        }





        println("width출력")
        println(getScreenWidth(this))
//수정

        val width_px = Resources.getSystem().displayMetrics.widthPixels
        val height_px =Resources.getSystem().displayMetrics.heightPixels
        val pixeldpi = Resources.getSystem().displayMetrics.densityDpi
        println("pixeldpi"+pixeldpi)


        val width_dp = width_px / pixeldpi * 160
        val height_dp = height_px / pixeldpi * 160
        println("widthdp"+width_dp)


        fun pxToDp2(px: Int): Int {
            val displayMetrics = context?.resources?.displayMetrics
            return (px / (displayMetrics!!.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }



        //

        //픽셀을 dp로 변경
        fun pxToDp(context: Home, px: Int): Float {
            val displayMetrics = context.resources.displayMetrics
            return px / displayMetrics.density
        }



        println("width픽셀로 출력")
        println(pxToDp(this,getScreenWidth(this)))
        val widthpx=pxToDp2(getScreenWidth(this))
        println("widthpx"+widthpx)


        val seekbar_x = -1*pxToDp(this,getScreenWidth(this))

        mseekbar.translationX= (-0.85*width_px.toFloat()/2).toFloat()//최종코드
            //(-2*widthpx.toFloat()/2).toFloat()//(-1.4*(width_dp.toDouble())).toFloat()
        //seekbar_veryfat.translationX = (-0.55*width_px.toFloat()/2).toFloat()

//        val mSeekBar = binding.seekBar
//        //이미지 겹칠 때 앞서게 설정
//        //val image1=binding.imageView4
//        //val image2=binding.imageView5
//        //image2.bringToFront();
//
        mseekbar.isEnabled = false //
        // Set the min, max and current
        // values to the SeekBar
//        //tes

        var mMin = 0
        var mMax = 300
        var mCurrent = 20

        mseekbar.min = mMin
        mseekbar.max = mMax

        //bmi계산 후 seekbar의 thumb위치 설정
        var weight:Double
        var height:Double


        weight=80.0
        height=1.74

        var bmi:Double



        bmi=weight/(height*height)*10
        if(bmi>300){
            bmi=300.0
        }


        println(bmi)

        mseekbar.progress=bmi.toInt()
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
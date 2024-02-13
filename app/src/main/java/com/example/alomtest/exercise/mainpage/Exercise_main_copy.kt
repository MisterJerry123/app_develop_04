package com.example.alomtest.exercise.mainpage
import SharedPreferenceUtils
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import retrofit2.Callback

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentExerciseMainCopyBinding
import com.example.alomtest.exercise.custompage01.doing_exercise
import com.example.alomtest.exercise.custompage02.exercise_add_custom_list
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse12
import com.example.alomtest.retrofit.email
import com.example.alomtest.retrofit.exercise_routine_list
import com.example.alomtest.retrofit.exerise_preset_list
import com.example.alomtest.routineIndicator
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.Collections.list
import kotlin.system.exitProcess


class exercise_main_copy : Fragment() {
    private lateinit var binding:FragmentExerciseMainCopyBinding
    private var backPressedTime: Long = 0


    //val numbers: Array<Int> = arrayOf(1, 6,11)
    //private val binding get() = _binding!!

    lateinit var routininfo:LinearLayout
    var cnt=0



//    private val test_data = listOf(
//        exercise_routine_profile("길똥이의 아침운동", 2),
//        exercise_routine_profile("길똥이의 점심운동", 3),
//        exercise_routine_profile("길똥이의 저녁운동", 4),
//        exercise_routine_profile("길똥이의 야간운동", 6),
//        exercise_routine_profile("길똥이의 새벽운동", 4),
//
//
//    )

    var test_data = mutableListOf<exercise_routine_profile>()
    var loadedUserRoutineFromServer=ArrayList<exercise_routine_list>()//기존에 저장한 운동루틴 불러오는 것






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentExerciseMainCopyBinding.inflate(layoutInflater)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()

                if (currentTime - backPressedTime > 3000) {
                    // 첫 번째 뒤로가기
                    backPressedTime = currentTime
                    // 3초 안에 두 번 뒤로가기를 누르면 앱 종료

                    Toast.makeText(requireContext(), "버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        backPressedTime = 0
                    }, 3000)
                } else {
                    // 두 번째 뒤로가기
                    requireActivity().finish()
                    exitProcess(0)
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
        //요소간 여백 넣어주는 코드
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing) // 여백 크기
        val recyclerView = binding.routineList
        //loadedUserRoutineFromServer = ArrayList<exercise_routine_list>()

        //est_data.add(exercise_routine_profile("길똥이의 새벽운동", 4))




        // RecyclerView에 LayoutManager 설정 및 ItemDecoration 추가
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(exercise_routine_divider(spacingInPixels))
        //loadedUserRoutineFromServer=load_exercise()//기존에 저장한 운동루틴 불러오는 것
        //Log.d("loadedUserRoutineFromServer",loadedUserRoutineFromServer.toString())
        load_exercise()






}

    override fun onResume() {
        super.onResume()

    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")

    }


    private fun init(routineListdata:ArrayList<routineIndicator>){
        Log.d("routineListdata", routineListdata.toString())
        SharedPreferenceUtils.saveData(requireContext(),"routine_cnt", routineListdata.size.toString())
        binding.routineList.layoutManager = LinearLayoutManager(requireContext())
       val adapter = exercise_routine_adapter(requireContext(), routineListdata,loadedUserRoutineFromServer){
            clickedItem->
            val bundle = Bundle()

           Log.d("어댑터 클릭 감지","")
            bundle.putString("","")
            val fragment = doing_exercise()
            fragment.arguments=bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack(null)
                .commit()

        }
        binding.routineList.adapter = adapter
        //추가//

    }

    private fun load_exercise() {
        val api = Api.create()

        val jsonObject = JSONObject()
        val email: String = SharedPreferenceUtils.loadData(requireContext(), "email")
        val usertoken: String = SharedPreferenceUtils.loadData(requireContext(), "accessToken")
        jsonObject.put("email", email)

        api.load_preset_exercise_list(
            accessToken = "Bearer $usertoken",
            JsonParser.parseString(jsonObject.toString())
        ).enqueue(object : Callback<ArrayList<exercise_routine_list>> {
            override fun onResponse(
                call: Call<ArrayList<exercise_routine_list>>,
                response: Response<ArrayList<exercise_routine_list>>
            ) {
                Log.d("로그인 통신 성공", response.toString())
                Log.d("로그인 통신 성공", response.body().toString())
                Log.d("response코드", response.code().toString())

                when (response.code()) {
                    200 -> {
                        Toast.makeText(requireContext(), "200OK", Toast.LENGTH_SHORT).show()
                        Log.d("받은 정보 로그", response.toString())
                        val result = response.body()!!
                        handleResult(result)
                    }
                    // 다른 상태 코드에 대한 처리
                }
            }

            override fun onFailure(call: Call<ArrayList<exercise_routine_list>>, t: Throwable) {
                Log.d("로그인 통신 실패", t.message.toString())
                Log.d("로그인 통신 실패", "fail")
            }
        })
    }

    private fun handleResult(result: ArrayList<exercise_routine_list>) {
        Log.d("Result", result.toString())
        loadedUserRoutineFromServer.clear()
        loadedUserRoutineFromServer.addAll(result)
        // 여기서 데이터 처리가 완료되었으므로 UI를 업데이트하는 등의 작업을 수행
        val routineIndicators = responseDataToRoutineIndicator(result)
        Log.d("추출한 데이터 출력", routineIndicators.toString())
        // UI 업데이트 등의 작업을 이어서 진행
        init(routineIndicators)
    }

    private fun responseDataToRoutineIndicator(input:ArrayList<exercise_routine_list>): ArrayList<routineIndicator> {
        var routineIndicator=ArrayList<routineIndicator>()
        for(i:Int in 0 until input.size){
            routineIndicator.add(routineIndicator(input[i].presetName,input[i].splitPresetDto.size))
        }
        return routineIndicator
    }




private var _binding: FragmentExerciseMainCopyBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExerciseMainCopyBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }




    companion object {


    }
}
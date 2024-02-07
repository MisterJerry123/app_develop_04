package com.example.alomtest.exercise.custompage01

import SharedPreferenceUtils
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.MyViewModel
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentAddRoutinePageBinding
import com.example.alomtest.exercise.mainpage.exercise_main_copy
import com.example.alomtest.exerciseData
import com.example.alomtest.exercise.custompage02.exercise_add_custom_list
import com.example.alomtest.exercise.custompage03.exercise_selcet_list_adapter
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.custom_exercise_data_class
import com.example.alomtest.retrofit.exercise_list
import com.google.gson.JsonParser
import org.json.JSONObject


class add_routine_page : Fragment() {

    private lateinit var binding: FragmentAddRoutinePageBinding
    private lateinit var viewmodel : MyViewModel
    lateinit var exercise_recycler_view : RecyclerView
    var custom_cnt:Int =0
    var receive_data: String? = " " //공백으로 하면 되고 왜 null로 하면 안되는거지?



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentAddRoutinePageBinding.inflate(layoutInflater)

        val addbtn = binding.exerciseAddBtn
        val backicon=binding.cancelicon

        val usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")
        var email=(SharedPreferenceUtils.loadData(requireContext(),"email",""))

        val bundle = arguments

        if(bundle?.getString("exercise_name")==null){
            receive_data=" "
        }
        else{
            receive_data = bundle.getString("exercise_name").toString()
        }

        Log.d("번들 테스트", receive_data.toString())





        addbtn.setOnClickListener {
            println("test1")
            replaceFragment(exercise_add_custom_list())
            println("test2")

        }
        backicon.setOnClickListener {
            replaceFragment(exercise_main_copy())
        }

        binding.saveIcon.setOnClickListener {
            val api = Api.create()

            val jsonObject= JSONObject()
            jsonObject.put("email",email)


            //preset DTO를 jsonObject

            val presetDTO = ArrayList<custom_exercise_data_class>()

            for(i:Int in 0 until viewmodel._myList.value!!.size){
                for(j:Int in 0 until viewmodel._myList.value!![i].set_list.size){

                    val presetNumber = 0 //일단 0으로 가정
                    val exerciseNumber= i
                    val presetTitle= binding.exerciseTitle.text.toString()
                    val weight= viewmodel._myList.value!![i].set_list[j].weight
                    val setCount = j
                    val repetitionCount= viewmodel._myList.value!![i].set_list[j].cnt
                    val exerciseName = viewmodel._myList.value!![i].exerciseName
                    val category = "not need maybe"

                    presetDTO.add(custom_exercise_data_class(presetNumber,exerciseNumber,presetTitle,weight,setCount,repetitionCount,exerciseName,category))





/*
    val presetNumber: Int,
    val exerciseNumber: Int,
    val presetTitle: String,
    val weight: Int,
    val setCount: Int,
    val repetitionCount: Int,
    val exerciseName: String,
    val category: String
)*/

                }
            }

            jsonObject.put("presetDto",presetDTO)


            Log.d("저장 버튼 누른 후 JSON출력", presetDTO.toString())


//
////
//            api.custom_exercise_save(accessToken = "Bearer $usertoken", JsonParser.parseString(jsonObject.toString()))
//                .enqueue(object : Callback<LoginBackendResponse2> {
//                override fun onResponse(
//                    call: Call<LoginBackendResponse2>,
//                    response: Response<LoginBackendResponse2>
//                ) {
//                    Log.d("로그인 통신 성공",response.toString())
//                    Log.d("로그인 통신 성공", response.body().toString())
//                    Log.d("response코드",response.code().toString())
//
//                    when (response.code()) {
//                        200-> Toast.makeText(this@account2,"인증코드를 이메일로 발송했습니다.", Toast.LENGTH_SHORT).show()
//                        401-> Toast.makeText(this@account2,"인증코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//                        403-> Toast.makeText(this@account2,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//                        404 -> Toast.makeText(this@account2, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
//                        500 -> Toast.makeText(this@account2, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginBackendResponse2>, t: Throwable) {
//                    Log.d("로그인 통신 실패",t.message.toString())
//                    Log.d("로그인 통신 실패","fail")
//                }
//            })
//        }
        }



        //backicon은 나중에 구현




    //View.findViewById(R.id.exercise_view)






    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        val customList = arrayListOf(
//            exerciseData("대흉근 발달, 3대 운동","01 바벨 벤치 프레스")
//
//            )
        //data class ExerciseData(val title: String, val description: String)

        var customList = ArrayList<exerciseData>()
        viewmodel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        //customList.add(exerciseData("대흉근 발달, 3대 운동","01 바벨 벤치 프레스"))



        println("routine page 진입1")
        exercise_recycler_view = view.findViewById(R.id.exercise_view)
        println("routine page 진입2")
        exercise_recycler_view.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        println("routine page 진입3")
        exercise_recycler_view.setHasFixedSize(true)
        println("routine page 진입4")

        exercise_recycler_view.adapter = viewmodel._myList.value?.let { exercise_list_adpater(viewmodel,
            viewmodel._myList.value!!
        ) }
        println("routine page 진입5")

        Log.d("if문 진입 전 receive data 확인", receive_data.toString())
        Log.d("true or false", (receive_data!!.isNotBlank()).toString())
        if(receive_data!!.isNotBlank()){

            Log.d("exerciseData 추가하는 곧 진입",receive_data.toString())
            //customList.add(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
            viewmodel.addItem(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
            viewmodel._myList.value!!.get(viewmodel._myList.value!!.size-1).set_list.add(0,set_list_item(0,1))

//            if(viewmodel._myList.value?.size==0){
//                viewmodel.addItem(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
//                viewmodel._myList.value[0].set_list.add()
//            }
//            else{
//                viewmodel.addItem(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
//
//            }


            //Log.d("커스텀 리스트 출력", customList.toString())
            Log.d("커스텀 리스트 출력", viewmodel._myList.value.toString())
            receive_data=""

//            val ad=exercise_recycler_view.adapter
//            ad?.notifyDataSetChanged()

            binding.courseNo.text="총 ${viewmodel._myList.value?.size}코스"
        }

        for(i:Int in 0 until viewmodel._myList.value!!.size){
            Log.d("세트리스트 크기 출력", (viewmodel._myList.value!![i].set_list.size).toString())
            if(viewmodel._myList.value!![i].set_list.size==0){
                viewmodel._myList.value!!.removeAt(i)
                viewmodel.deleteItem(i)

            }

        }

    }
    private var _binding: FragmentAddRoutinePageBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddRoutinePageBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }

    override fun onResume() {
        super.onResume()
        binding.courseNo.text="총 ${viewmodel._myList.value?.size}코스"
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")

    }

    companion object {

    }
}
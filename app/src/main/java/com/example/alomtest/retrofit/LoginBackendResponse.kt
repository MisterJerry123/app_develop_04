package com.example.alomtest.retrofit

data class UserModel(
    var id : String ?= null,
    var pw : String ?= null
)

data class email_authetication(
    var email:String?=null,
    var code:Int?=null,
)

data class email(
    var toEmail:String,
    var title:String,
    var authCode:String,
    var flag:Int)

data class LoginBackendResponse(
    val code: Int=0,
    val error: String="",
    val message: String="",
    val status: Int=0,
    val timestamp: String="",

    val grantType:String="",
    val accessToken:String="",
    val refreshToken:String=""
)
data class LoginBackendResponse2(
    val code: Int=0,
    val bool:Boolean=false,
)
data class LoginBackendResponse3(
    val code: Int=0,
    val bool:Boolean=false,
)
//data class LoginBackendResponse4(
//    val code: Int=0,
//    val bool:Boolean=false,
//)
data class LoginBackendResponse5(
    val code: Int=0,
    val bool:Boolean=false,
)
data class LoginBackendResponse6(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String
)
data class LoginBackendResponse7(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String,
    val height: Double,
    val weight: Double,
    val bmi:Double,
    val gender: String,
    val birthday:String,
    val name :String,
    val email:String,


)
data class LoginBackendResponse8(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String
)
data class LoginBackendResponse9(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String
)
data class LoginBackendResponse10(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String
)
data class LoginBackendResponse11(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String
)
data class LoginBackendResponse12(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String
)
data class LoginBackendResponse13(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String,
    val refreshToken: String

)

data class LoginBackendResponse14(
    val code: Int=0,
    val bool:Boolean=false,
    val accessToken: String,
    val refreshToken: String,

    val list:Array<exercise_list>



)

//data class exerciseList(
//    .id(id)
//.name(name)
//.calorie(calorie)
//.category(category)

//)

data class exercise_list(
    val id:String,
    val name:String,
    val calorie:String,
    val category:String
)

data class food_list(//서버로 부터 받은 음식 정보들을 받는 data class
    val name:String,
    val carbohydrate:Double,
    val protein:Double,
    val fat:Double,
    val calorie:Double,
    val mass :Double,
)

data class custom_exercise_data_class(val presetNumber:Int, val exerciseNumber:Int, val presetTitle : String, val weight:Int, val setCount : Int, val repetitionCount:Int, val exerciseName:String, val category:String
)
/*
    "presetNumber": 0,
    "exerciseNumber": 0,
    "presetTitle": "string",
    "weight": 0,
    "setCount": 0,
    "repetitionCount": 0,
    "exerciseName": "string",
    "category": "string"
*/

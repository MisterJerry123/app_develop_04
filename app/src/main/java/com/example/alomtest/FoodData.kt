package com.example.alomtest

import org.json.JSONObject

data class FoodData(val title : String)
//    companion object {
//        fun fromString(jsonString: String): FoodData {
//            val jsonObject = JSONObject(jsonString)
//            return FoodData(jsonObject.getString("title"))
//        }
//    }
//
//    fun toJsonString(): String {
//        return JSONObject().apply {
//            put("title", title)
//        }.toString()
//    }


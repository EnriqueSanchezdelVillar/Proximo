package com.example.primo.proximoconecta.Api

import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Activities.UserActivity
import com.example.primo.proximoconecta.Activities.UserData
import com.example.primo.proximoconecta.Login.Login
import com.example.primo.proximoconecta.Login.UserLogin
import com.example.primo.proximoconecta.Register.Register
import retrofit2.Call
import retrofit2.http.*


interface ApiService
{//​
  //Post
  @POST("api/v1/auth/singup")
  fun signUp(@Body registrer: Register): Call<Login>
  //​
  @POST("api/v1/auth/login")
  fun login(@Body userLogin: UserLogin): Call<Login>
  //​//
  @POST("api/v1/activities")
  fun createActivity(@Body activity_ong: Activity, @Header("Authorization") authorization: String): Call<Activity>
  //​//
  @POST("api/v1/users/activities")
  fun createUserActivity(@Body activity_ong: UserActivity, @Header("Authorization") authorization: String): Call<UserActivity>

  // GET
  //activities ONG
  @GET("api/v1/users/{userid}/activities")
  fun getActivityOng(@Path("userid") id: String?, @Header("Authorization") authorization: String ): Call<List<Activity>>
  //​
  // all activities
  //​
  @GET("api/v1/activities")
  fun getActivity(@Header("Authorization") authorization: String): Call<List<Activity>>
  //​
  //SingleAcctivity

  @GET("api/v1/activity/{activityId}")
  fun getOneActivity(@Path("activityId") id: String?,@Header("Authorization") authorization: String): Call<Activity>
  //​
  //
  @GET("api/v1/{activityId}/{userId}/users")
fun getUserfromActivity(@Path("activityId") id: String?, @Path("userId") id2: String, @Header("Authorization") authorization: String): Call<List<Activity>>

  // GET USER DATA
  @GET("api/v1/user/me")
  fun getUserData(@Header("Authorization") authorization: String): Call<UserData>

  //Delete

  //PUT
  @PUT("/activities/{activityId}")
  fun updateActivity(@Path("activityId") id: String, @Body activity_ong: Activity, @Header("Authorization") authorization: String): Call<Activity>
  //​
}
//api/v1/users/5d19dedfd888ec460e8c0f90/activities
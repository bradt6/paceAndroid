package com.example.vitamon

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("createPatient")

    fun createPatient(@Field ("email") email : String,
                      @Field ("firstName") firstName : String,
                      @Field ("gender") gender : String,
                      @Field ("idDevice") idDevice : Int,
                      @Field ("lastName") lastName : String,
                      @Field ("medicareNumber") medicareNumber : Int,
                      @Field ("phoneNumber") phoneNumber : Int,
                      @Field ("postcode") postcode : Int,
                      @Field ("state") state : String,
                      @Field ("streetNumber") streetNumber : String,
                      @Field ("streetName") streetName: String,
                      @Field ("suburb") suburb : String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("userlogin")

    fun userlogin(
        @Field("username") username: String,
        @Field("passwordChoice") passwordChoice: String
    ): Call<LoginResponseClass>

    @GET("allpatients")
    fun getPatients():Call<allPatients>



}
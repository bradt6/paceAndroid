package com.example.vitamon

import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

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


}
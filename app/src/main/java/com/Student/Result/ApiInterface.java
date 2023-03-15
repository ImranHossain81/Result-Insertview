package com.Student.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("insert.php")
    Call<Modelresultlist> insertresultlist (@Body Modelresultlist modelperson);

    @POST("deleteStd.php")
    Call<Modelresultlist> deleteresultlist (@Body Modelresultlist modelperson);

    @POST("update.php")
    Call<Modelresultlist> UpdatePerson (@Body Modelresultlist modelPerson);

    @GET("getData.php")
    Call<List<Modelresultlist>> getAllData();



}

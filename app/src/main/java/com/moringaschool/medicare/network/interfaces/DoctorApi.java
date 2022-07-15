package com.moringaschool.medicare.network.interfaces;

import com.moringaschool.medicare.models.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoctorApi {
    @GET("/doctor")
     Call<List<Doctor>> getResults(
            //name it as it is in the API documentation
    );

}

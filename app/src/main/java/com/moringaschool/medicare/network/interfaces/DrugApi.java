package com.moringaschool.medicare.network.interfaces;

import com.moringaschool.medicare.models.DrugList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DrugApi {
    @GET("{slug}")
    Call<DrugList> getInfo(@Path("slug") String slug);
}

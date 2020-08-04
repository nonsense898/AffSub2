package com.example.affsub2.REST;

import com.example.affsub2.REST.Model.Main;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface AffApi {
    @GET
    Call<Main> getResults(@Url String url);
}

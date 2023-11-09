package com.example.api;

import com.example.models.Person;
import com.example.models.PersonList;
import com.example.wrapper.PercentWrapper;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


public interface API {
    @GET("/persons/ping")
    Call<PercentWrapper> getPing();

    @GET("/persons/all")
    Call<List<Person>> getPersons();
}

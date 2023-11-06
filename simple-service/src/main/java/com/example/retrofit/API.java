package com.example.retrofit;

import com.example.models.Person;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface API {
    @GET("persons/")
    Call<List<Person>> getPersons();
}

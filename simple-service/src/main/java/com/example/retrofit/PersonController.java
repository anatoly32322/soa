package com.example.retrofit;

import com.example.api.API;
import com.example.models.Person;
import com.example.models.PersonList;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonController {

    public List<Person> start() throws IOException {
        API api = ServiceGenerator.createService(API.class);

        Call<List<Person>> call = api.getPersons();
        Response<List<Person>> resp = call.execute();
        if (resp.body() == null) {
            return new ArrayList<>();
        }
        return resp.body();
    }
}

package com.example.retrofit;

import com.example.models.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class PersonController implements Callback<List<Person>> {

    static final String BASE_URL = "http://localhost:8080/demography-1.0/api/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<List<Person>> call = api.getPersons();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
        if(response.isSuccessful()) {
            List<Person> personList = response.body();
            assert personList != null;
            personList.forEach(person -> System.out.println(person.getId()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Person>> call, Throwable t) {
        t.printStackTrace();
    }
}

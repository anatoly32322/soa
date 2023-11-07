package com.example.retrofit;

import com.example.models.Person;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;

public class PersonController implements Callback<ArrayList<Person>> {

    static final String BASE_URL = "http://localhost:8080/demography-1.0/api/";

    public void start() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
                    @Override
                    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString());
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<ArrayList<Person>> call = api.getPersons();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
        if(response.isSuccessful()) {
            List<Person> personList = response.body();
            assert personList != null;
            personList.forEach(person -> System.out.println(person.getId()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
        t.printStackTrace();
    }
}

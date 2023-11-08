package com.example.retrofit;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class ServiceGenerator {

    private static final String BASE_URL = "http://localhost:8080/";

    private static Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
                        @Override
                        public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                            return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString());
                        }
                    })
                    .setLenient()
                    .create();

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
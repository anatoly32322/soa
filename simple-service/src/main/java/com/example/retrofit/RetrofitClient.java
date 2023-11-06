package com.example.retrofit;

import com.example.resource.MyResource;
import retrofit2.Retrofit;

public class RetrofitClient {
    private static final String BASE_URL = "http://localhost:8080/demography-1.0/api";
    private static Retrofit retrofit = null;
    public final MyResource myResource;

//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory()
//        }
//    }

    public RetrofitClient() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/demography-1.0/api")
                .build();
        myResource = retrofit1.create(MyResource.class);
    }
}

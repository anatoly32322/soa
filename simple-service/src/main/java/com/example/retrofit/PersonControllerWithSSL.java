//package com.example.retrofit;
//
//import com.example.models.Person;
//import com.example.models.PersonList;
//import com.google.gson.*;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import java.lang.reflect.Type;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.List;
//import java.time.ZonedDateTime;
//import javax.net.ssl.*;
//
//public class PersonControllerWithSSL implements Callback<ArrayList<Person>> {
//
//    static final String BASE_URL = "http://localhost:8080/persons/all/";
//
//    public void start() {
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
//                    @Override
//                    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString());
//                    }
//                })
//                .create();
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        TrustManager[] trustAllCerts = new TrustManager[]{ new X509TrustManager() {
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//
//            public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//            }
//
//            public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//            }
//        }};
//
//        SSLContext sslContext = null;
//        try {
//            sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new SecureRandom());
//        } catch (KeyManagementException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        assert sslContext != null;
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
//                .hostnameVerifier((hostname, session) -> true)
//                .addInterceptor(logging)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        API api = retrofit.create(API.class);
//
//        Call<PersonList> call = api.getPersons();
//        call.enqueue(this);
//    }
//
//    @Override
//    public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
//        if(response.isSuccessful()) {
//            List<Person> personList = response.body();
//            assert personList != null;
//            personList.forEach(person -> System.out.println(person.getId())); } else { System.out.println(response.errorBody()); } }
//
//    @Override
//    public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
//        t.printStackTrace();
//    }
//}
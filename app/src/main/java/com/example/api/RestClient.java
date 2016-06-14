package com.example.api;

import com.example.model.Classroom;
import com.example.model.School;
import com.example.model.User;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Filipi Andrade on 09-Jun-16.
 */
public class RestClient {

    private static final String BASE_URL = "http://10.0.3.2:8080/Restful-API-TAG/api/TagService/";
    private static TAGApiInterface tagApiInterface;

    public static TAGApiInterface getClient() {
        try {
            if (tagApiInterface == null) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                return chain.proceed(chain.request());
                            }
                        }).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                tagApiInterface = retrofit.create(TAGApiInterface.class);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return tagApiInterface;
    }

    public interface TAGApiInterface {

        @GET("getCredentials/{username}/{password}")
        Call<ArrayList<User>> getCredentials(@Path("username") String username, @Path("password") String password);

        @GET("getSchools")
        Call<ArrayList<School>> getSchools();

        @GET("getClassroomsBySchoolInep/{school_inep_fk}")
        Call<ArrayList<Classroom>> getClassroomsBySchoolInep(@Path("school_inep_fk") String school_inep_fk);
    }

}

package com.taxon_mobile.api;

import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiEndPoint {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("upgrade-power")
    Call<User.Stat> upgradePower(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("save-user-stats")
    Call<User.Stat> saveUserStat(
            @Header("Authorization") String token,
            @Field("power") int power,
            @Field("evo") int evo,
            @Field("dna") int dna,
            @Field("point") int point
    );
}

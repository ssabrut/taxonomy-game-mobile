package com.taxon_mobile.api;

import com.taxon_mobile.models.Creature;
import com.taxon_mobile.models.Evolution;
import com.taxon_mobile.models.Leaderboard;
import com.taxon_mobile.models.Log;
import com.taxon_mobile.models.LoginResponse;
import com.taxon_mobile.models.LogoutResponse;
import com.taxon_mobile.models.Quiz;
import com.taxon_mobile.models.RegisterResponse;
import com.taxon_mobile.models.ShowUserCreature;
import com.taxon_mobile.models.UserCreature;
import com.taxon_mobile.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("name") String name,
            @Field("school") String school,
            @Field("city") String city,
            @Field("birthyear") String birthYear
    );

    @POST("logout")
    Call<LogoutResponse> logout(
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

    @FormUrlEncoded
    @POST("user-evolutions")
    Call<Evolution> unlockEvolution(
            @Header("Authorization") String token,
            @Field("evolution_id") int evolutionId
    );

    @FormUrlEncoded
    @POST("unlock-creature")
    Call<Creature> unlockCreature(
            @Header("Authorization") String token,
            @Field("species_id") int speciesId
    );

    @FormUrlEncoded
    @POST("log")
    Call<Log> log(
            @Header("Authorization") String token,
            @Field("model") String model,
            @Field("description") String description
    );

    @GET("upgrade-power")
    Call<User.Stat> upgradePower(
            @Header("Authorization") String token
    );

    @GET("user-creatures")
    Call<UserCreature> userCreatures(
            @Header("Authorization") String token
    );

    @GET("creatures")
    Call<Creature> creatures(
            @Header("Authorization") String token
    );

    @GET("creatures/{species_id}")
    Call<ShowUserCreature> showCreature(
            @Header("Authorization") String token,
            @Path(value = "species_id", encoded = true) String speciesId
    );

    @GET("quiz")
    Call<Quiz> quiz(
            @Header("Authorization") String token
    );

    @GET("evolutions")
    Call<Evolution> evolution(
            @Header("Authorization") String token
    );

    @GET("leaderboard")
    Call<Leaderboard> leaderboard(
            @Header("Authorization") String token
    );
}

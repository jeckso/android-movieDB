package com.jeckso.moviedb.data.network.rest.service

import com.jeckso.moviedb.data.models.AuthResponseModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    companion object {
        private const val AUTHORIZATION_ENDPOINT = "/auth"
        private const val LOGIN_ENDPOINT = "$AUTHORIZATION_ENDPOINT/login"
        private const val REGISTER_ENDPOINT = "$AUTHORIZATION_ENDPOINT/create"
    }

    @POST(LOGIN_ENDPOINT)
    @FormUrlEncoded
    suspend fun authorize(
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthResponseModel

    @POST(REGISTER_ENDPOINT)
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthResponseModel

}
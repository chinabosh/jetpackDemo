package com.bosh.jetpackdemo.net

import com.bosh.jetpackdemo.entity.UserInfo
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

/**
 * @author lzq
 * @date  2022/2/18
 */
interface UserService {

    @GET("login/login")
    suspend fun login(
        @Query("name") name: String,
        @Query("pwd") pwd: String
    ) : UserInfo
}
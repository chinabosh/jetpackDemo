package com.bosh.jetpackdemo.net

/**
 * @author lzq
 * @date  2022/2/18
 */
data class ServiceManager(
    val userService: UserService,
    val oilService: OilService
)
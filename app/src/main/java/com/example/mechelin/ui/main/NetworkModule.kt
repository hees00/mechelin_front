package com.example.mechelin.ui.main

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException



object ApiClient {
        private const val BASE_URL = "https://dev.mechelin.shop"

    val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()


        fun getRetrofit(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .client(provideOkHttpClient(AppInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun provideOkHttpClient(interceptor: AppInterceptor): OkHttpClient
                = OkHttpClient.Builder().run {
            addInterceptor(interceptor)
            build()
        }

        class AppInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("X-ACCESS-TOKEN", "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDQzMzk4NDIsImV4cCI6MTY0NTgxMTA3MX0.mabi8DnjOp6nL2oDeMDgNcl5ZowTp0x9BIIeSmREp6Q")
                    .build()
                proceed(newRequest)
            }
        }
    }
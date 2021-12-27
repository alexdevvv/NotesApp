package com.example.notesapp.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitKeeper private constructor(){
//    companion object{
//        private var retrofit: Retrofit? = null
//
//        fun getInstance(): Retrofit? {
//            if(retrofit == null) {
//                retrofit = Retrofit.Builder().baseUrl("https://demo-maven.herokuapp.com/")
//                    .client(OkHttpClient.Builder()
//                        .addInterceptor(AuthInterceptor())
//                        .addInterceptor(HttpLoggingInterceptor().apply {
//                        level = HttpLoggingInterceptor.Level.BODY
//                    }).build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build()
//            }
//            return retrofit
//        }
//    }
//}
//
//class AuthInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val newRequest = request.newBuilder()
//            .build()
//        return chain.proceed(newRequest)
//    }
}
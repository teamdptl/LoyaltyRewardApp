package com.example.loyaltyrewardapp.data.api

import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance {
    private const val BASE_URL = "https://app.dy.id.vn/api/"
    private val auth = FirebaseAuth.getInstance()

    private val retrofit: Retrofit by lazy {
        val builder: Builder = Builder()

        builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc2MDI3MTI2ODJkZjk5Y2ZiODkxYWEwMzdkNzNiY2M2YTM5NzAwODQiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTmd1eeG7hW4gVHXhuqVuIFbFqSIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9sb3lhbHR5cmV3YXJkLTk1MDMzIiwiYXVkIjoibG95YWx0eXJld2FyZC05NTAzMyIsImF1dGhfdGltZSI6MTcxNDc5NDk2MCwidXNlcl9pZCI6ImtPV2RVQzFWcnpmTnQxejdpZHk2OThUdDJhQTIiLCJzdWIiOiJrT1dkVUMxVnJ6Zk50MXo3aWR5Njk4VHQyYUEyIiwiaWF0IjoxNzE0Nzk0OTYwLCJleHAiOjE3MTQ3OTg1NjAsImVtYWlsIjoiKzg0MzEyMzQ1Njc4QGFwcC52biIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicGhvbmVfbnVtYmVyIjoiKzg0MzEyMzQ1Njc4IiwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyIrODQzMTIzNDU2NzhAYXBwLnZuIl0sInBob25lIjpbIis4NDMxMjM0NTY3OCJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.TLuDCql2-W_cGyecbAs9ceICmYF79jMXstnP0fRMJUq4X65wtIbCxVNR8FGOC0m6-3mDDJXJtEwdYCN2FjpViO5QeO1QPXI9GWTiLzMRD2_0fIRnMyP0D28U70cdrgNdpUtVPoIxJx1lkCyi5RAp_S9vTs52ZMcGvkpsrvoPGfk_XvcEFE1uNqn2VLyd5741-GgsM8U1NqHL6UESykU8qTz5tHsKFG-foxbi5AyeDvo0Jdws2hkLW7uoqnzdUF6CP0Zmy71gC5i_KToy6IUJDIjoPP66api2xReWYGHJvscrX5eLLPuZUd4WDkBBk0TewR6Pu8HidxL7zQmS3tZUBQ").build()
            )
        })
        val client: OkHttpClient = builder.build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
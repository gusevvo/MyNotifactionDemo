package com.example.mynotifactiondemo.di

import android.webkit.CookieManager
import com.example.mynotifactiondemo.BuildConfig
import com.example.mynotifactiondemo.data.api.ApiClientInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.cookieJar(object : CookieJar {
            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                val cookieList = mutableListOf<Cookie>()
                val cookie = CookieManager.getInstance().getCookie(url.toString())
                val cookieParts = cookie.split("[,;]")
                for (part in cookieParts) {
                    val parsed = Cookie.parse(url, part.trim())
                    if (parsed != null) {
                        cookieList.add(parsed)
                    }
                }
                return cookieList
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                var cookie: Cookie? = null
                if (url.pathSegments.contains("Login")) {
                    cookie = cookies.firstOrNull { it.expiresAt > System.currentTimeMillis() }
                } else if (url.pathSegments.contains("Logout")) {
                    cookie = cookies.firstOrNull { it.expiresAt <= System.currentTimeMillis() }
                }
                if (cookie != null) {
                    val cookieManager = CookieManager.getInstance()
                    cookieManager.setCookie(cookie.domain, cookie.toString())
                }
            }
        })

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiClientInterface =
        retrofit.create(ApiClientInterface::class.java)
}
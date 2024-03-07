package com.example.rickadnmorty_62.di

import com.example.rickadnmorty_62.data.network.Repository
import com.example.rickadnmorty_62.data.network.SimpleApi
import com.example.rickadnmorty_62.ui.SharedViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        provideRetrofit(get())

    }
    single {
        provideOkHttpClient(get())
    }
    single {
        dispatcherIO()
    }
    factory {
        provideCartoonApiService(get())
    }
    single {
        provideLoggingInterceptor()
    }

    factory {
        Repository(get())
    }
    viewModel{
        SharedViewModel(get())
    }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideCartoonApiService(retrofit: Retrofit): SimpleApi {
    return retrofit.create(SimpleApi::class.java)
}
fun dispatcherIO() = Dispatchers.IO
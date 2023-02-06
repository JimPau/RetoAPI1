package com.demj.retoconsumodeapi1.services;

import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    //Aqui estableceremos el llamado a la API ademas convertira y pasara los datos
    // traidos de gson del Objeto por ejemplo de la clase movie

    private static Retrofit retrofit;
    public static Retrofit getService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

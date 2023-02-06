package com.demj.retoconsumodeapi1.services;

import com.demj.retoconsumodeapi1.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
// INTERFAZ , aqui se declara todas las operaciones que se desea consumir
public interface ApiPost {
    // Crear:
    //- Clic derecho en el paquete creado "services"
    //- New/Java Class/interface
    //
    //Crear método o función:
    //- Nombre: getPosts
    //- Tipo: GET
    //- EndPoint a utilizar: /posts

    //Describir todas las operaciones que solicitaremos de la api,agregar,listar,etc
    @GET("/posts")
    // mwtodo que se envargara de consumir la operacion
    Call<List<Post>> getPosts();
}

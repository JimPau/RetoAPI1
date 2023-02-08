package com.demj.retoconsumodeapi1.services;

import com.demj.retoconsumodeapi1.models.Post;
import com.demj.retoconsumodeapi1.models.Comments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    // metodo que se envargara de consumir la operacion -- consumira el post
    Call<List<Post>> getPosts();


    //En interface, agregar los nuevos métodos GET
    @GET("/posts/{id}")
    //public Call<List<Post>> getPostById(@Path("id") String id);

    Call<Post>  getPostById(@Path("id") Integer id);

    //>> getCommentsByPostId()


    @GET("/posts/{id}/comments")
    Call<List<Comments>> getCommentsByPostId(@Path("id") Integer postId);



}

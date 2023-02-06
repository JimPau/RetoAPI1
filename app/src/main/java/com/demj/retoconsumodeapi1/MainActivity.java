package com.demj.retoconsumodeapi1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.demj.retoconsumodeapi1.adapter.PostAdapter;
import com.demj.retoconsumodeapi1.models.Post;
import com.demj.retoconsumodeapi1.services.ApiPost;
import com.demj.retoconsumodeapi1.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Post> posts;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext())); // ERROR
        showPost();
    }

    //metodo encargado de ejecutar el llamado de api
    public void showPost(){

        Call<List<Post>> call= ApiService.getService().create(ApiPost.class).getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            // Se ejecutara si llega una respuesta por parte del servidor
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){
                    posts=response.body();

                   //tvid.setText(posts.get(tvid));
                    // aqui se debe poner asignar a cada uno de los controles lo datos de API

                    postAdapter=new PostAdapter(posts,getApplicationContext()); // este codigo llama los datos del objeto del postadapter?
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override// Devolvera una respuesta en caso ocurra un error de conexion de internet o algun error
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
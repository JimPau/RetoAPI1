package com.demj.retoconsumodeapi1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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

  //  private TextView txtitulo, txcomentario;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext())); // ERROR

        //txtitulo=findViewById(R.id.tvtitle);
       //txcomentario=findViewById(R.id.tvbody);
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

                    Log.d("", "LISTA DE POST: " + response.body());
                    for (int i = 0; i < posts.size()-1; i++) {
                        Log.d("", "posts; " + posts.get(i).getId() + " " +posts.get(i).getTitle());

                        //Intent pasar= new Intent(MainActivity.this,PostDetailsActivity.class);
                      // pasar.putExtra("Titulo",txtitulo.getText().toString());
                      // pasar.putExtra("Comentario",txcomentario.getText().toString());

                    }
                   //tvid.setText(posts.get(tvid));
                    // aqui se debe poner asignar a cada uno de los controles lo datos de API

                    postAdapter=new PostAdapter(posts,MainActivity.this); // este codigo llama los datos del objeto del postadapter?
                    recyclerView.setAdapter(postAdapter); // EL ADAPTADOR  le transfiere o muestra al recyclerview
                }
            }

            @Override// Devolvera una respuesta en caso ocurra un error de conexion de internet o algun error
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
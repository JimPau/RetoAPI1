package com.demj.retoconsumodeapi1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demj.retoconsumodeapi1.adapter.CommentsAdapter;
import com.demj.retoconsumodeapi1.models.Comments;
import com.demj.retoconsumodeapi1.models.Post;
import com.demj.retoconsumodeapi1.models.TitlePost;
import com.demj.retoconsumodeapi1.services.ApiPost;
import com.demj.retoconsumodeapi1.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailsActivity extends AppCompatActivity {

    // Para el listado de los comments
    private List<Comments> comments;
    private RecyclerView recyclerView;
    //Utilizar el adaptador commnetsadapter
    private CommentsAdapter commentsAdapter;

    // no es necesario creo
    private List<Post> titlePosts;

    private TextView textViewIdPost, textViewTitle, textViewContent;
   private int IdPost=1;


   private String Postcoment ,Postitulo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
       // txt_titulo = findViewById(R.id.txt_titulo);
       // txt_comentario = findViewById(R.id.txt_comentario);

       //*IdPost=(TextView)findViewById(R.id.txt_titulo); // llama el ID

        textViewTitle = (TextView) findViewById(R.id.txt_titulo);
        textViewContent = (TextView) findViewById(R.id.txt_comentario);


       //Comments=(TextView) findViewById(R.id.txt_comentario);
        //Intent pasar= new Intent(MainActivity.this,PostDetailsActivity.class);
        //pasar.putExtra("Titulo",txtitulo.getText().toString());
        // pasar.putExtra("Comentario",txcomentario.getText().toString());

      //coment =(TextView)findViewById(R.id.txt_comentario);


        if (getIntent().getExtras() != null) {
            IdPost = getIntent().getIntExtra("idPost", 0);

            Postcoment = getIntent().getStringExtra("Comentario");
            Postitulo = getIntent().getStringExtra("Titulo");

            textViewTitle.setText(Postitulo);
            textViewContent.setText(Postcoment);


            Log.d("", "===================================");
            Log.d("", "idpost: " + IdPost);
            Log.d("", "===================================");
        }
        recyclerView = findViewById(R.id.rv_com);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showComments();
        //showTitlePost();
    }
// Title
    //public void showTitlePost() {
      //  Call<Post> call = ApiService.getService().create(ApiPost.class).getPostById(IdPost);
      //  call.enqueue(new Callback<List<Post>>() {
        //    @Override
         //   public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

            //    if (response.isSuccessful()) {
                 //   titlePosts = response.body();

                 //   textViewIdTitle.setText(titlePosts.getTitle());
                  //  textViewIdContent.setText(titlePosts.getBody());

            //    }
        //    }

          //  @Override
        //    public void onFailure(Call<List<Post>> call, Throwable t) {
         //       Toast.makeText(PostDetailsActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
        //    }
    //    });
 //   }





    //metodo encargado de ejecutar el llamado a la api
    public void showComments() {
                                                              // ApiPost -> La Interface
        Call<List<Comments>> call = ApiService.getService().create(ApiPost.class).getCommentsByPostId(IdPost);// 0 o 1?
        call.enqueue(new Callback<List<Comments>>() {
            //Se van a crear 2 metodos

//Permitira recuperar de gson y pasarlo a nuestro  objeto (comments)tipo lista para luego pasar a nuestro adapter y se encargue de pasarlo
// a nuestro recyclerview

            // Se ejecutara si llega una respuesta por parte del servidor
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (response.isSuccessful()){
                    comments=response.body();

                    commentsAdapter=new CommentsAdapter(comments,getApplicationContext()); // este codigo llama los datos del objeto del commentsadapter?
                    recyclerView.setAdapter(commentsAdapter); // EL ADAPTADOR  le transfiere o muestra al recyclerview
                }

            }

            @Override
            // Devolvera una respuesta en caso ocurra un error de conexion de internet o algun error
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Toast.makeText(PostDetailsActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
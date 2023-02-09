package com.demj.retoconsumodeapi1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.demj.retoconsumodeapi1.adapter.AlbumsAdapter;
import com.demj.retoconsumodeapi1.models.Albums;
import com.demj.retoconsumodeapi1.services.ApiPost;
import com.demj.retoconsumodeapi1.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsActivity extends AppCompatActivity {
// almacenar el listado de peliculas
    private List<Albums> albums;

    private RecyclerView recyclerView;

    //utilizar el adaptadoralbum
    private AlbumsAdapter albumsAdapter;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        recyclerView=findViewById(R.id.rv_albums);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        showAlbums();
    }

    //metodo para ejecutar el llamado api
    public void showAlbums(){

        Call<List<Albums>> call= ApiService.getService().create(ApiPost.class).getAlbums();
        call.enqueue(new Callback<List<Albums>>() {

            //recuperar el gson y pasarlo añ objeto tipo lista Albums
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if(response.isSuccessful()){
                    albums=response.body();

                    albumsAdapter=new AlbumsAdapter(albums, AlbumsActivity.this);//con este código
                    // llamas  los datos de album solicitados y los almacena en albumsAdapter
                    recyclerView.setAdapter(albumsAdapter);// y el adaptador le transfiere los datos
                    //recylcerview

                }

            }

            // Devolvera una respuesta en caso ocurra un error de conexion de internet o algun error
            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Toast.makeText(AlbumsActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
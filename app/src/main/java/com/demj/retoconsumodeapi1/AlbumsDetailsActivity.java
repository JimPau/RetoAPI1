package com.demj.retoconsumodeapi1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.demj.retoconsumodeapi1.adapter.PhotoAdapter;
import com.demj.retoconsumodeapi1.models.Photos;
import com.demj.retoconsumodeapi1.services.ApiPost;
import com.demj.retoconsumodeapi1.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsDetailsActivity extends AppCompatActivity {

    //Para el listado de las imagenes
    private List<Photos> photos;

    private RecyclerView recyclerView;

    //Utilizar el adaptador albumsAdapter
    private PhotoAdapter photoAdapter;

    private String Title;
    int IdAlbum;


    //*
    // private String TituloPhoto;

    private TextView Titulo;

    private String Albumscoment, AlbumsTitulo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_details);

        Titulo = (TextView) findViewById(R.id.txt_tituloalbum);


        // textId = (TextView) findViewById(R.id.txt_tituloalbum);

        //textViewContent =(TextView) findViewById(R.id.) --> era el contenido del anterior

        if (getIntent().getExtras() != null) {
            // IdAlbums = getIntent().getIntExtra("AlbumId", 0);
            Title = getIntent().getStringExtra("TituloAlbum");
            IdAlbum = getIntent().getIntExtra("AlbumId", 0);

            Titulo.setText(Title);

            //*
            //TituloPhoto = getIntent().getStringExtra("TituloPhot");

            //Titulo.setText(TituloAlbum);
            //textId.setText(IdAlbums);

            //Albumscoment= getIntent().getStringExtra("URL");
            // AlbumsTitulo= getIntent().getStringExtra("Titulo");

            // textViewTile.setText(AlbumsTitulo);
            //textViewContent.setText(Albumscoment);

        }
        recyclerView = findViewById(R.id.rv_fotos);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        showPhotos();
        //albumphoto();


    }

    //metodo encargado de ejecutar el llamado a la api
    public void showPhotos() {
        Call<List<Photos>> call = ApiService.getService().create(ApiPost.class).getPhotos();
        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (response.isSuccessful()) {
                    photos = response.body();

                    photoAdapter = new PhotoAdapter(photos, getApplicationContext(), AlbumsDetailsActivity.this);
                    recyclerView.setAdapter(photoAdapter);
                    albumphoto();

                }

            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });

    }

    //-------------------------------------------------
    public void albumphoto() {
        List<Photos> photosList = photos;
        List<Photos> FiltrerResult = new ArrayList<>();
        for (int i = 0; i < photosList.size(); i++) {
            if (photosList.get(i).getAlbumId() == IdAlbum) {
                Photos photo = photosList.get(i);
                /*Log.d("", "=======================");
                Log.d("", "album: (" + photo.getAlbumId() + ") " + photo.getId() + " " + photo.getTitle());
                Log.d("", "=======================");*/
                FiltrerResult.add(photo);
            }
        }
        photoAdapter = new PhotoAdapter(FiltrerResult, getApplicationContext(), AlbumsDetailsActivity.this);
        photoAdapter.TitleAlbum = Title;
        recyclerView.setAdapter(photoAdapter);
        //-------------------------------------------------
    }

}
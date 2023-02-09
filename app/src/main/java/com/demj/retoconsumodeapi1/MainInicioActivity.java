package com.demj.retoconsumodeapi1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainInicioActivity extends AppCompatActivity {

    private CardView cv_post, cv_album;

// boton de inicio envia a post
    public void enviarpost (View view) {
        Intent i = new Intent(MainInicioActivity.this, MainActivity.class);
        startActivity(i);

    }

    // boton de inicio envia a album
    public void enviaralbum (View view) {
        Intent i = new Intent(MainInicioActivity.this, AlbumsActivity.class);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);

 // llamamos al cardview
    //*    cv_post.setOnClickListener(new View.OnClickListener() {
      //*      @Override
        //*    public void onClick(View v) {
           //*     Intent btnpost = new Intent(MainInicioActivity.this,MainActivity.class);
           //*     startActivity(btnpost);
       //*     }
     //*   });

     //*   cv_album.setOnClickListener(new View.OnClickListener() {
     //*       @Override
        //*    public void onClick(View v) {
        //*        Intent btnalbum = new Intent(MainInicioActivity.this,PostDetailsActivity.class);
         //*       startActivity(btnalbum);

       //*     }
      //*  });
    }

}
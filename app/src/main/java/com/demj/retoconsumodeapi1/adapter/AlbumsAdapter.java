package com.demj.retoconsumodeapi1.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demj.retoconsumodeapi1.AlbumsDetailsActivity;
import com.demj.retoconsumodeapi1.R;
import com.demj.retoconsumodeapi1.models.Albums;

import java.util.List;

// Permitira incorporar dentro de recylclerview el diseño y componentes que contiene el archivo item_albums
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    //al escribir extends se autogenera lo demas (abajo)

 //Se guardara los datos que se solicito en el albums
    private List<Albums> albums;

    //
     private Activity activity;

     //Se generara un constructor para inicializar los objetos creados arriba


    public AlbumsAdapter(List<Albums> albums, Activity activity) {
        this.albums = albums;
        this.activity = activity;
    }

    @NonNull
    @Override
    //Esepcifica el xml para ello se utiliza el siguiente , especificando el aqrchvo item_albums
    public AlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_albums,parent,false);
        return new ViewHolder(view);
    }

    // Coloca los componentes de nuestro item.xml_album en este caso es el titulo
    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvname_albums.setText(albums.get(position).getTitle());

        // onclick en el cardvire para enviar a otro main o llamar al cardview
        holder.cv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // aqui indicas a donde vas as enviar al hacer click(otro main)
                Intent i = new Intent(activity, AlbumsDetailsActivity.class);
                i.putExtra("AlbumId",albums.get(position).getId());
                i.putExtra("TituloAlbum",albums.get(position).getTitle());
                activity.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount()
    {
        return albums.size();
    }

    // Se creo la clase ViewHolder
    // Acceder los valores del widgets
    //conectar mediante identificadores
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvname_albums;

        private CardView cv_click;

        // que archivos de componentes del archivo item_albums se utilizaran para mostrar los
        // datos de gson.

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname_albums=itemView.findViewById(R.id.tvname_albums);
            cv_click=itemView.findViewById(R.id.cvclick);

        }
    }
}

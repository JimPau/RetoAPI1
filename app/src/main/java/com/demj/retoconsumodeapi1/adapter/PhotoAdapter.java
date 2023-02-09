package com.demj.retoconsumodeapi1.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demj.retoconsumodeapi1.R;
import com.demj.retoconsumodeapi1.models.Photos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    //Se guardara los datos que se solicito en el albums
    private List<Photos> photos;

    private Activity activity;

    private TextView tvTituloImg, tvTituloAlbum;

    public String TitleAlbum;

    private ImageView imgdialog;


    private Context context;

    //Se generara un constructor para inicializar los objetos creados arriba

    public PhotoAdapter(List<Photos> photos, Context applicationContext, Activity activity) {
        this.photos = photos;
        this.activity = activity;
    }
    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_galeria, parent, false);
        return new ViewHolder(view);

    }
    // Coloca los componentes de nuestro item.xml_album en este caso es el titulo
    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_TituloImagen.setText(photos.get(position).getTitle());

        // Libreria picasso
        Picasso.get().load(photos.get(position).getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground).into(holder.imagenView);

        //showDialog();
        //onclick en el cardvire para enviar a otro main o llamar al cardview
        holder.cardView.setOnClickListener(v -> {
            String Tituloimg = getTitle(position);

            showDialog(Tituloimg, photos.get(position).getUrl());
        });
    }


    public String getTitle(int position) {
        return photos.get(position).getTitle();
    }


    //  showDialog(String title,String url
    public void showDialog(String title, String url) { // llamas al ALERT DIALOG


        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.alert_dialog, null);

        imgdialog = (ImageView) view.findViewById(R.id.imgImagen);
        // llamar al titulo de la imagen
        tvTituloImg = (TextView) view.findViewById(R.id.txt_tituloimg);
        tvTituloImg.setText(title);

        // llamar al titulo de la imagen
        tvTituloAlbum = (TextView) view.findViewById(R.id.txt_tituloalbum);
        tvTituloAlbum.setText(TitleAlbum);

        // llamar a la imagen
        ImageView imgImagem = (ImageView) view.findViewById(R.id.imgImagen);


        Picasso.get().load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground).into(imgImagem);


        // ImageView imagephoto =(ImageView)view.findViewById(R.id.imagenView);
        // tvTituloAlbum = view.findViewById(R.id.txt_tituloimg);
        // tvTituloAlbum.setText(tvTituloAlbum);*/


        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setView(view)
                .create();
        alertDialog.show();


        //tvTituloAlbum =(TextView) view.findViewById(R.id.txt_tituloimg);
        //tvTituloAlbum.setText(title);


        //* }


        // Acceder los valores del widgets
        //conectar mediante identificadores

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenView;
        private TextView tv_TituloImagen;

        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_TituloImagen = itemView.findViewById(R.id.tvtituloimagen);
            imagenView = itemView.findViewById(R.id.imagenView);
            cardView = itemView.findViewById(R.id.cardviewphotos);

        }
    }
}



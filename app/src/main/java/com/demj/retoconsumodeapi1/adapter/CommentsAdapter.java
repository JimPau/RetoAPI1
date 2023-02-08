package com.demj.retoconsumodeapi1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demj.retoconsumodeapi1.PostDetailsActivity;
import com.demj.retoconsumodeapi1.R;
import com.demj.retoconsumodeapi1.models.Comments;

import java.util.List;

public class CommentsAdapter extends  RecyclerView.Adapter<CommentsAdapter.ViewHolder>{
    // Se encarga de guardar el listado de comment
    private List<Comments> comments;

    //permitir acceder al contexto del activity main que muestra el recyclerview
    private Context context;

    public CommentsAdapter(List<Comments> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override   // Metodo oncreateviewholder -> especifica el xml que se desea inflar en el recylceview
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(view);

    }

    //Su funcion es colocarlos en los componentes de nuestro item_comments el name, correo y comentario
    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {

        holder.tvnombre.setText(comments.get(position).getName());
        holder.tvcorreo.setText(comments.get(position).getEmail());
        holder.tvcomentario.setText(comments.get(position).getBody());

        //Glide .with(context).load(movies.get(position).getPortada()).into(holder.iv_portada);

        //se pone aqui?
        //Intent i = new Intent(activity, PostDetailsActivity.class);
        //i.putExtra("Nombre",comments.getText().toString());
       // i.putExtra("Correo",comments.getText().toString());
       // i.putExtra("Comentario",comments.getText().toString());
    }

    @Override
    public int getItemCount() {

        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvnombre, tvcorreo, tvcomentario;

        // que archivos de componentes del archivo item_comments se utilizaran para mostrar los
        // datos de gson.
        public ViewHolder(View view) {
            super(view);
            tvnombre= view.findViewById(R.id.tvnombre);
            tvcorreo= view.findViewById(R.id.tvcorreo);
            tvcomentario= view.findViewById(R.id.tvcomentario);

        }
    }
}

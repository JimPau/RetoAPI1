package com.demj.retoconsumodeapi1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demj.retoconsumodeapi1.R;
import com.demj.retoconsumodeapi1.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // guardar  los datos que se solicito en el post
    private List<Post> posts;

    //Permitira accceder al contexto de activity main que muestra el recylcer view
//    private Context context;


    //Generamos un constructor para inicializar  los objetos creados arriba
    public PostAdapter(List<Post> posts, Context context) {

        this.posts = posts;
       // this.context = context;
    }

    @NonNull
    @Override      // Metodo oncreateviewholder -> especifica el xml que se desea inflar en el recylceview
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post,parent,false);
         return new ViewHolder(view);
    }

    @Override  //Su funcion es colocar los en los componentes de neustro item_post el id, titulo y body
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // el id es un integer pero al mostrar lo hace en forma de texto, por eso el integer se debe convertir en string, existen 3 formas :
        holder.tvid.setText(posts.get(position).getId().toString());
        holder.tvid.setText(String.valueOf(posts.get(position).getId()));
        holder.tvid.setText(posts.get(position).getId()+ " ");

        holder.tvtitle.setText(posts.get(position).getTitle());
        holder.tvbody.setText(posts.get(position).getBody());





    }
    //Tiene que ver con el tamaño
    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvid, tvtitle, tvbody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid=itemView.findViewById(R.id.tvid);
            tvtitle=itemView.findViewById(R.id.tvtitle);
            tvbody=itemView.findViewById(R.id.tvbody);

        }
    }
}

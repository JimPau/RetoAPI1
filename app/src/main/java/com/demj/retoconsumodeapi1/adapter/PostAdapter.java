package com.demj.retoconsumodeapi1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demj.retoconsumodeapi1.PostDetailsActivity;
import com.demj.retoconsumodeapi1.R;
import com.demj.retoconsumodeapi1.models.Post;

import java.util.List;


// Permitira incorporar dentro de recylclerview el diseño y componentes que contiene el archivo item_post
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // guardar  los datos que se solicito en el post
    private List<Post> posts;

    //Permitira accceder al contexto de activity main que muestra el recycler view
   //private Context context;

   //activity es para el boton ?
     private Activity activity;


    //Generamos un constructor para inicializar  los objetos creados arriba
    public PostAdapter(List<Post> posts, Activity activity) {

        this.posts = posts;
        this.activity = activity;
       // comentario


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
       // holder.tvid.setText(String.valueOf(posts.get(position).getId()));
        // holder.tvid.setText(posts.get(position).getId()+ " ");

        holder.tvtitle.setText(posts.get(position).getTitle());
        holder.tvbody.setText(posts.get(position).getBody());

        // Que aparezca el id , body
        //holder.tvtitle.setText("id: " +posts.get(position).getTitle());
       // holder.tvbody.setText("Body: " + posts.get(position).getBody());



        //llamar al cardview
        holder.cv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // enviar los datos

                Intent i = new Intent(activity, PostDetailsActivity.class);
                //Intent i = new Intent(activity, PostDetailsActivity.class);

                // pasar el titulo y comentario a activity details .xml
              i.putExtra("idPost",posts.get(position).getId());
              //i.putExtra("Correo",posts.get(position).getTitle());
              i.putExtra("Comentario",posts.get(position).getBody());
                i.putExtra("Titulo",posts.get(position).getTitle());
                activity.startActivity(i);
            }

        });

    }
    //Tiene que ver con el tamaño
    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Acceder los valores del widgets
    //conectar mediante identificadores

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvid, tvtitle, tvbody;
         private CardView cv_click;

        // private txt_titulo ,txt_comentario;

        // que archivos de componentes del archivo item_post se utilizaran para mostrar los
        // datos de gson.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid=itemView.findViewById(R.id.tvid);
            tvtitle=itemView.findViewById(R.id.tvtitle);
            tvbody=itemView.findViewById(R.id.tvbody);
            cv_click=itemView.findViewById(R.id.cvclick);

        }


    }
}

package com.example.manthansingh.pokev1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder>{

    private Context ctx;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> imagename= new ArrayList<>();
    String searchby;

    public PokeAdapter(Context ctx, ArrayList<String> images, ArrayList<String> imagename) {
        this.ctx = ctx;
        this.images = images;
        this.imagename = imagename;
        this.searchby = "namesearch.php";
    }

    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.eachelement, null);
        return new PokeViewHolder(view);

        /*View view= LayoutInflater.from(ctx).inflate(R.layout.eachelement,viewGroup,false);
        PokeViewHolder pokeViewHolder = new PokeViewHolder(view);
        return pokeViewHolder;*/
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder pokeViewHolder, final int i) {

        Glide.with(ctx).asBitmap().load(images.get(i)).into(pokeViewHolder.pokeimage);
        pokeViewHolder.pokename.setText(imagename.get(i));
        pokeViewHolder.relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(ctx,MainPage3.class);
                intent3.putExtra("search",imagename.get(i));
                intent3.putExtra("searchby",searchby);
                ctx.startActivity(intent3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagename.size();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder{

        CircleImageView pokeimage;
        TextView pokename;
        RelativeLayout relativelayout;
        public PokeViewHolder(@NonNull View itemView) {
            super(itemView);

            pokeimage=itemView.findViewById(R.id.eachimage);
            pokename=itemView.findViewById(R.id.eachname);
            relativelayout=itemView.findViewById(R.id.relative_layout);
        }
    }
}
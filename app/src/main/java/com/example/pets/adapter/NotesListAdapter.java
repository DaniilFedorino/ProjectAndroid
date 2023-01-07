package com.example.pets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pets.BitmapConvert;
import com.example.pets.NotesClickListener;
import com.example.pets.R;
import com.example.pets.models.Notes;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> list;

    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_breed.setText(list.get(position).getBreed());

        holder.textView_age.setText(list.get(position).getAge());
        holder.textView_age.setSelected(true);
        holder.imageView_pet_mini.setImageBitmap(BitmapConvert.bitmapIncrease(BitmapConvert.getBitmapfromByteArray(list.get(position).getImage())));

       // holder.imageView_pet_mini.setImageURI(Uri.parse(list.get(position).getImage()));

        if(list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.pin_icon);
        }else{
            holder.imageView_pin.setImageResource(0);
        }

        holder.notes_conteiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_conteiner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()),holder.notes_conteiner);
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredList){
        list=filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {
    CardView notes_conteiner;
    TextView textView_title;
    ImageView imageView_pin;
    TextView textView_breed;
    TextView textView_age;
    ImageView imageView_pet_mini;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_conteiner=itemView.findViewById(R.id.notes_conteiner);
        textView_title=itemView.findViewById(R.id.textView_title);
        imageView_pin=itemView.findViewById(R.id.imageView_pin);
        textView_breed=itemView.findViewById(R.id.textView_breed);
        textView_age=itemView.findViewById(R.id.textView_age);
        imageView_pet_mini=itemView.findViewById(R.id.imageView_pet_mini);
    }
}

package com.example.pets;

import androidx.cardview.widget.CardView;

import com.example.pets.models.Notes;

public interface NotesClickListener {

    void onClick(Notes notes);

    void onLongClick(Notes notes, CardView cardView);
}

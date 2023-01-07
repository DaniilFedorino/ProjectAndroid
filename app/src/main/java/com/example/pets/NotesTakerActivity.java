package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pets.models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class NotesTakerActivity extends AppCompatActivity {

    EditText editText_title;
    EditText editText_breed;
    EditText editText_age;
    EditText edittext_passportData;
    EditText edittext_vaccinations;
    ImageView imageView_save;
    ImageView imageView_pet;
    FloatingActionButton fabImage;
    Bitmap bitmap;
    Notes notes;
    boolean isOldNote=false;
    private final int Pick_image = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_breed = findViewById(R.id.editText_breed);
        editText_age = findViewById(R.id.editText_age);
        edittext_passportData = findViewById(R.id.editText_passportData);
        edittext_vaccinations = findViewById(R.id.editText_vaccinations);
        imageView_pet = findViewById(R.id.imageView_pet);
        fabImage = findViewById(R.id.fab_addImage);
        bitmap=((BitmapDrawable)imageView_pet.getDrawable()).getBitmap();



        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            editText_breed.setText(notes.getBreed());
            editText_age.setText(notes.getAge());
            edittext_passportData.setText(notes.getPassportData());
            edittext_vaccinations.setText(notes.getVaccinations());
            imageView_pet.setImageBitmap(BitmapConvert.bitmapIncrease(BitmapConvert.getBitmapfromByteArray(notes.getImage())));
            bitmap=((BitmapDrawable)imageView_pet.getDrawable()).getBitmap();

           /* final InputStream imageStream ;
            imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            imageView_pet.setImageBitmap(selectedImage);*/

            isOldNote = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText_title.getText().toString();
                String breed = editText_breed.getText().toString();
                String age = editText_age.getText().toString();
                String passportData = edittext_passportData.getText().toString();
                String vaccinations = edittext_vaccinations.getText().toString();


                if (title.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please, enter title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (breed.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please, enter breed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (age.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please, enter age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passportData.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please, enter passport data", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (vaccinations.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please, enter vaccinations", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isOldNote) {
                    notes = new Notes();
                }
                notes.setTitle(title);
                notes.setBreed(breed);
                notes.setAge(age);
                notes.setPassportData(passportData);
                notes.setVaccinations(vaccinations);
                bitmap=((BitmapDrawable)imageView_pet.getDrawable()).getBitmap();

                byte[] image= BitmapConvert.getByteArrayfromBitmap(BitmapConvert.bitmapCrop(bitmap));

                notes.setImage(image);

                //notes.setImage(imageUri.toString());

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        fabImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
        {
            super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

            switch (requestCode) {
                case Pick_image:
                    if (resultCode == RESULT_OK) {
                        try {
                            imageUri = imageReturnedIntent.getData();
                            final InputStream imageStream ;
                                imageStream = getContentResolver().openInputStream(imageUri);
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            selectedImage=BitmapConvert.bitmapRotate(selectedImage);
                            imageView_pet.setImageBitmap(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
}
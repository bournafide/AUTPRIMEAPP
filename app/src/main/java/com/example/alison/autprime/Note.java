package com.example.alison.autprime;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Note extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Long dateNtime;
    private boolean viewOrupdate;
    private String fileName;
    private theNote loadedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar bar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(bar);

        fileName = getIntent().getStringExtra("NOTE_FILE");

        //Checks if file is existing, if so update, if not create a new note
        if(fileName != null && !fileName.isEmpty() && fileName.endsWith(NoteDatabase.file_type))
        {
            loadedNotes = NoteDatabase.viewNoteName(getApplicationContext(), fileName);

            if(loadedNotes != null)
            {
                title.setText(loadedNotes.getNTitle());
                content.setText(loadedNotes.getNContent());
                dateNtime = loadedNotes.getNDateTime();

                viewOrupdate = true;
            }
            else
            {
                dateNtime =  System.currentTimeMillis();
                viewOrupdate = false;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu); //save and delete button layout

        title = (EditText) findViewById(R.id.note_title); //user input title
        content = (EditText) findViewById(R.id.note_content); //user input content

        return true;
    }

    @Override //Button click handling
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.actionSaveNote:
                saveNote();
                break;

            case R.id.actionDeleteNote:
                deleteNote();
                break;


        }
        return true;
    }

    //Validate if note is saved or not then save
    private void saveNote(){
        String t = title.getText().toString();
        String c = content.getText().toString();

        if (loadedNotes != null) {
            dateNtime = loadedNotes.getNDateTime(); //creating a new note
        } else
        {
            dateNtime = System.currentTimeMillis(); //updating an existing note
        }

        //Save
        if(NoteDatabase.saveNote(this, new theNote(t, c, dateNtime)) == true){
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }


        finish(); //return to Notepad
    }

    public void deleteNote() {

        //note in-progress but isn't saved and user clicks to delete
        if(loadedNotes == null)
        {
            finish();
        }
        else
        {
            AlertDialog.Builder message = new AlertDialog.Builder(this).setTitle("Are you sure?").setMessage("You are about to delete " + title.getText().toString())
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            NoteDatabase.deleteNote(getApplicationContext(), loadedNotes.getNDateTime() + NoteDatabase.file_type);
                            Toast.makeText(getApplicationContext(),  title.getText().toString()+ " is deleted", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //Do nothing
                    .setNegativeButton("No", null)
                    .setCancelable(false);
            message.show();
        }


        Intent delete = new Intent(Note.this, Notepad.class);
        startActivity(delete);
    }

}

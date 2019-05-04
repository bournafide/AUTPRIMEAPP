package com.example.alison.autprime;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar bar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(bar);

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
                /**
            case R.id.note_delete:
                Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
                break;
                 */

        }
        return true;
    }

    //Validate if note is saved or not
    private void saveNote(){
        theNote newNote = new theNote(title.getText().toString(), content.getText().toString()
        ,System.currentTimeMillis());

        if(NoteDatabase.saveNote(this, newNote)){
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    /**
    public void noteSaved(View view) {
        Intent save = new Intent(Note.this, Notepad.class);
        startActivity(save);
    }

    public void noteDeleted(View view) {
        Intent delete = new Intent(Note.this, Notepad.class);
        startActivity(delete);
    }
     */

}

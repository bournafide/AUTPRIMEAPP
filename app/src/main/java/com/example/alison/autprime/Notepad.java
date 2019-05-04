package com.example.alison.autprime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Notepad extends AppCompatActivity {

    private ListView npListNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_activity);
        npListNotes = (ListView) findViewById(R.id.np_notes_list);
    }

    //add_note button in Notepad class directs user to Note class
    public void addNote(View view) {
        Intent clicked = new Intent(Notepad.this, Note.class);
        startActivity(clicked);
    }


    @Override //Load notes saved in storage file
    protected void onResume() {
        super.onResume();

        npListNotes.setAdapter(null);

        ArrayList<theNote> notepadList = NoteDatabase.readNotes(this);

        if(notepadList == null || notepadList.size() == 0)
        {
            Toast.makeText(this, "No existing saved notes", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
                NoteAdapter na = new NoteAdapter(this, R.layout.note_list, notepadList);
                npListNotes.setAdapter(na);
        }

    }

}








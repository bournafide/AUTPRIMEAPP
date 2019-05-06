package com.example.alison.autprime;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NoteDatabase {

    public static final String file_type = ".bin";
    public static FileInputStream input;
    public static ObjectInputStream read;

    //Saving note to a file
    public static Boolean saveNote(Context c, theNote n){
        String filename= String.valueOf(n.getNDateTime() )+ file_type; //setting file name
        FileOutputStream output; //open file
        ObjectOutputStream write; //write file
        try{
            output = c.openFileOutput(filename, c.MODE_PRIVATE); //save in private storage area of the app
            write= new ObjectOutputStream(output);
            write.writeObject(n);
            write.close();;
            output.close();

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<theNote> readNotes(Context c) {
        ArrayList<theNote> notes = new ArrayList<>();

        File nDir = c.getFilesDir();
        ArrayList<String> nFiles = new ArrayList<>();

        //Checks if file is the user's notes
        for (String file : nDir.list()) {
            if (file.endsWith(file_type)) {
                nFiles.add(file);
            }
        }

        //Display each note
        for (int i = 0; i < nFiles.size(); i++) {
            try {
                input = c.openFileInput(nFiles.get(i));
                read = new ObjectInputStream(input);

                notes.add((theNote) read.readObject());

                input.close();
                read.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return notes;
    }

    public static theNote viewNoteName(Context c, String file_name) {
        File f = new File(c.getFilesDir(), file_name);

        if (f.exists() && !f.isDirectory()) {
            Log.v("NOTEDATABASE", "file exist = " + file_name);

            try {
                input = c.openFileInput(file_name);
                read = new ObjectInputStream(input);
                theNote aNote = (theNote) read.readObject();
                input.close();
                read.close();

                return aNote;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void deleteNote(Context c, String fileName) {
        File fDir = c.getFilesDir();
        File f = new File(fDir, fileName); //Checks if file exists

        if(f.exists())
        {
            f.delete();
        }
    }
}

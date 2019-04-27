package com.example.alison.autprime;
//this activity will be what the user sees if they login successfully
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    Button coursebtn, gradebtn, mapbtn, notebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        coursebtn = findViewById(R.id.courseId);
        gradebtn = findViewById(R.id.gradeId);
        mapbtn = findViewById(R.id.mapId);
        notebtn = findViewById(R.id.noteId);
        //retrieve data from main activity
        Intent intent = getIntent();
        String text = intent.getStringExtra("Username");

        //display the text for username in the placeholder for viewing text in this activity
        TextView viewText = findViewById(R.id.placeholderUsername);
        viewText.setText(text);

        //code for the buttons functions in the app
        //need to create four more activities that will appear when each button is clicked
        coursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to direct users to their courses
                //eg: Intent courseIntent = new Intent(Activity2.this, CourseActivity.class);
                //startActivity(courseIntent);
            }
        });
        gradebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to direct users to their course grades
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to direct users to AUT map
            }
        });
        notebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to direct users to a notepad
            }
        });
    }
}

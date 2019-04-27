package com.example.alison.autprime;
//main activity contains login activity for the app
//so when app launches, a login screen is the first thing the user will see
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    Button login_button;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.placeUsername);
        password = findViewById(R.id.placePassword);
        login_button = findViewById(R.id.loginButton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //need to add code to verify user login details
                //is correct with microsoft outlook before homepage is opened
                validate(username.getText().toString(),password.getText().toString());
            }
        });
    }
    //simple validating test for users network login and password
    //it does not however, validate actual users network login and password stored in Microsoft Outlook
    public void validate(String username, String password){
        if(!(username.isEmpty()) && !(password.isEmpty())){
            //print out non-error message and startup the next activity after login successful
            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
            EditText nameText = findViewById(R.id.placeUsername);
            String text = nameText.getText().toString();
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("Username", text);
            startActivity(intent);
        }
        else{
            //print out error message for invalid login
            Toast.makeText(getApplicationContext(),"Invalid login",Toast.LENGTH_SHORT).show();
        }
    }
}

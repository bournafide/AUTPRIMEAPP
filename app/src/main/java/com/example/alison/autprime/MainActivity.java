package com.example.alison.autprime;
//when user logs in successfully, they will be directed to the homepage (Activity2.java)
//no security measures have been implemented yet
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        login_button = findViewById(R.id.button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //need to add code to verify user login details
                //is correct with microsoft outlook before homepage is opened
                validate(username.getText().toString(),password.getText().toString());
            }
        });
    }
    public void validate(String username, String password){
        if(username.equals("user") && password.equals("pass")){
            //print out non-error message and startup the next activity after login successful
            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
        }
        else{
            //print out error message for invalid login
            Toast.makeText(getApplicationContext(),"Invalid login",Toast.LENGTH_SHORT).show();
        }
    }
}

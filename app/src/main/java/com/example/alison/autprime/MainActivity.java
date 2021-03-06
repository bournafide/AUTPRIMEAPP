package com.example.alison.autprime;
//main activity contains login activity for the app
//so when app launches, a login screen is the first thing the user will see
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        /*username = findViewById(R.id.placeHolderUsername);
        password = findViewById(R.id.placeHolderPassword);
        login_button = findViewById(R.id.loginButton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });*/
        WebView simpleWebView = (WebView) findViewById(R.id.simpleWebView);

        simpleWebView.setWebViewClient(new MyWebViewClient());

        String url = "https://blackboard.aut.ac.nz/webapps/login/?action=relogin";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load the url on the web view
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
            // load the url
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    //Unit testing section: Remember to change the validate method from public void to public String to start unit testing
    //Also remember to remove the (comments) double slash from the context of MainActivity below as well as the return strings in the validate method

    //public MainActivity(Context context){}

    /*public void validate(String username, String password){
        if(!(username.isEmpty()) && !(password.isEmpty())){
            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
            //return "Login was successful";
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Login",Toast.LENGTH_SHORT).show();
            //return "Login was unsuccessful";
        }
    }*/
}

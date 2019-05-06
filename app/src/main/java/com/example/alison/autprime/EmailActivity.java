package com.example.alison.autprime;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EmailActivity extends AppCompatActivity {
    WebView emailWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        emailWebView = findViewById(R.id.emailWebViewId);
        WebSettings webSetup = emailWebView.getSettings();
        webSetup.setJavaScriptEnabled(true);
        emailWebView.loadUrl("https://studentmail.aut.ac.nz/");
        emailWebView.setWebViewClient(new WebViewClient());


    }
    //public EmailActivity(Context context){}

    public class myWebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        if(emailWebView.canGoBack()){
            emailWebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }

}

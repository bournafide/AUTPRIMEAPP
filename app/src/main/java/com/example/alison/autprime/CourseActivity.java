package com.example.alison.autprime;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CourseActivity extends AppCompatActivity {
    WebView courseWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseWebView = findViewById(R.id.courseWebViewId);
        WebSettings webSetup = courseWebView.getSettings();
        webSetup.setJavaScriptEnabled(true);
        courseWebView.loadUrl("https://blackboard.aut.ac.nz/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_1_1");
        courseWebView.setWebViewClient(new WebViewClient());
    }
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
        if(courseWebView.canGoBack()){
            courseWebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}

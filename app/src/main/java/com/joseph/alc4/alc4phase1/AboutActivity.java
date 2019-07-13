package com.joseph.alc4.alc4phase1;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    private String andelaWebsiteuUl;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webView = findViewById(R.id.webViewAndelaUrl);
        andelaWebsiteuUl="https://andela.com/alc/";

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError (WebView view,
                                            SslErrorHandler handler,
                                            SslError error){
                handler.proceed();
            }

        });


        try{
            Log.d("AboutActivity", "onCreate: WebViewLoaded");
            webView.loadUrl(andelaWebsiteuUl);


        }catch (Exception e){
            Log.e("About Activity", "onCreate: Error loading url");
        }

    }

}

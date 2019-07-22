package com.joseph.alc4.alc4phase1;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webView = findViewById(R.id.webViewAndelaUrl);
        progressBar = findViewById(R.id.progressBar);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String andelaWebsiteUrl = "https://andela.com/alc/";

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError (WebView view,
                                            final SslErrorHandler handler,
                                            SslError error) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AboutActivity.this)
                        .setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                handler.proceed();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                handler.cancel();
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .setTitle(getString(R.string.warning_ssl))
                        .setMessage(getString(R.string.ssl_certificate_warning))
                        .setCancelable(false);
                alertDialog.create().show();
            }


            @Override
            public void onPageFinished(WebView view,String url){
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        try{
            Log.d("AboutActivity", "onCreate: WebViewLoaded");
            webView.loadUrl(andelaWebsiteUrl);


        }catch (Exception e){
            Log.e("About Activity", "onCreate: Error loading url");
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}

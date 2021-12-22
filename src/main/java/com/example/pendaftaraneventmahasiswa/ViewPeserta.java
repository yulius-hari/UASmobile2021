package com.example.pendaftaraneventmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewPeserta extends AppCompatActivity {
WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_peserta);

        webView1 = findViewById(R.id.webView1);
        webView1.setWebViewClient(new WebViewClient());
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setLoadsImagesAutomatically(true);
        webView1.loadUrl("http://cekberita.web.id/kelasmobile/view.php");
        webView1.reload();

    }
}
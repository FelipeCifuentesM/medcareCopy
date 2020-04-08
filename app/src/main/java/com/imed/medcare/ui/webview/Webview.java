package com.imed.medcare.ui.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.imed.medcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Webview extends AppCompatActivity {

    private WebView webView;
    private String url;
    private String title;
    @BindView(R.id.content_progressbar_webview)
    RelativeLayout contentProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_webview);

        Bundle bundle = getIntent().getExtras();
        ButterKnife.bind(this);

        if (bundle != null) {
            url = bundle.getString("URL");
            title = bundle.getString("TITLE");
        }

        initToolbar();
        initViews();
        setupWebView(webView, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }



    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_webview);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.webview);
    }

    @SuppressLint("JavascriptInterface")
    private void setupWebView(WebView webView, String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        Typeface font_regular = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");
        if(title.equalsIgnoreCase("registro")) {
            new MaterialDialog.Builder(Webview.this)
                    .title("¿Desea guardar?")
                    .content("Ud tiene cambios sin guardar")
                    .positiveText("Salir")
                    .positiveColor(ContextCompat.getColor(this, R.color.subtitle_my_profile))
                    .negativeText("Continuar")
                    .negativeColor(ContextCompat.getColor(this, R.color.dodger_blue))
                    .buttonRippleColor(ContextCompat.getColor(this, R.color.blue_grey))
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .typeface(font_regular,font_regular)
                    .show();
        }else {
            finish();
        }
    }

    private void manageLoaderShow() {
        contentProgressbar.setVisibility(View.VISIBLE);
    }

    private void manageLoaderDismiss() {
        contentProgressbar.setVisibility(View.GONE);
    }

    private class MyWebViewClient extends WebViewClient {



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            manageLoaderShow();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            manageLoaderDismiss();

            if(url.equalsIgnoreCase("http://mimed.jumpittlabs.cl/mobile/register/begin")){
                Toast.makeText(Webview.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            manageLoaderDismiss();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @TargetApi(android.os.Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
            onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
        }
    }
}

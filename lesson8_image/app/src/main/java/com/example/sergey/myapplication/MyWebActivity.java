package com.example.sergey.myapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import android.graphics.Canvas;
import android.graphics.Picture;

import android.net.Uri;
import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.ContextMenu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebViewClient;

import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.WebSettings;

public class MyWebActivity extends AppCompatActivity {

    private WebView mywebView;
    private static final String TAG = "tag";
    private ProgressDialog progressBar;
    private String imageBase64;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_web);

        this.mywebView = (WebView) findViewById(R.id.mywv);
        registerForContextMenu(mywebView);
        // mywebView.setOnCreateContextMenuListener(this);
        WebSettings settings = mywebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mywebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(MyWebActivity.this, "WebView Example", "Loading...");

        mywebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click..." + url);
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(MyWebActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
        mywebView.loadUrl("https://www.google.com.ua/search?q=image&prmd=ivn&source=lnms&tbm=isch&sa=X&ved=0ahUKEwj43dKzpLPRAhXCXiwKHcYsCwkQ_AUIBygB&biw=384&bih=511&dpr=2");

        mywebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
                Log.d(TAG, "onReceivedTouchIconUrl" + url + "\n" + precomposed);
            }
        });

        mywebView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                openContextMenu(v);
                Log.d("tag", "onLongClick: ");

                if (v instanceof WebView) {
                    HitTestResult result = ((WebView) v).getHitTestResult();
                    if (result != null) {
                        int type = result.getType();
                        String s = result.getExtra();
                        // Confirm type is an image

                        if (type == WebView.HitTestResult.IMAGE_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                            imageBase64 = s;

                            Log.d("tag", "imageBase64: " + imageBase64);
                            //Toast.makeText(MyWebActivity.this, url, Toast.LENGTH_LONG).show();
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.drawable.image);
        menu.setHeaderTitle(imageBase64);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_image:
                saveImage();
                //editNote(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void saveImage() {

        String url = imageBase64;

        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

            request.allowScanningByMediaScanner();

            request.setNotificationVisibility(DownloadManager
                    .Request
                    .VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,    //Download folder
                    "image.jpeg");                        //Name of file

            DownloadManager dm = (DownloadManager) getSystemService(
                    DOWNLOAD_SERVICE);

            dm.enqueue(request);
            Log.d("tag", "saved");
            Toast.makeText(this, "Картинка сохранена", Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            Toast.makeText(this, "Не удалось сохранить картинку", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}

package com.example.sergey.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import android.graphics.Canvas;
import android.graphics.Picture;

import android.net.Uri;
import android.os.Environment;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebViewClient;
import android.widget.Toast;

import android.webkit.WebSettings;

public class MyWebActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private WebView mywebView;
    private static final String TAG = "tag";
    private ProgressDialog progressBar;
    private String imageURL;
    private int REQUEST_WRITE_STORAGE = 1;

    void checkPermission() {
        Log.d(TAG, "checkPermission");
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
              
                return;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_web);
        mywebView = (WebView) findViewById(R.id.mywv);
        registerForContextMenu(mywebView);
        // mywebView.setOnCreateContextMenuListener(this);
        WebSettings settings = mywebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mywebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(MyWebActivity.this, "WebView Example", "Loading...");

        mywebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " +url);
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
        mywebView.loadUrl("https://www.google.com.ua/search?q=image&espv=2&biw=1680&bih=871&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi4s_iP4r_RAhUGYZoKHeXKBnkQ_AUIBigB");

        mywebView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if (ActivityCompat.checkSelfPermission(MyWebActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    checkPermission();
                } else {
                    openContextMenu(v);
                    Log.d("tag", "onLongClick: ");

                    if (v instanceof WebView) {
                        HitTestResult result = ((WebView) v).getHitTestResult();
                        if (result != null) {
                            int type = result.getType();
                            // Confirm type is an image
                            if (type == WebView.HitTestResult.IMAGE_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                                imageURL = result.getExtra();
                                Log.d("tag", "imageBase64: " + imageURL);
                                //Toast.makeText(MyWebActivity.this, url, Toast.LENGTH_LONG).show();
                            }
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
        menu.setHeaderTitle(imageURL);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_image:
                saveImage();
                  return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void saveImage() {
        String url = imageURL;

        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

            request.allowScanningByMediaScanner();

            request.setNotificationVisibility(DownloadManager
                    .Request
                    .VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,    //Download folder
                    "image.jpg");                        //Name of file

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

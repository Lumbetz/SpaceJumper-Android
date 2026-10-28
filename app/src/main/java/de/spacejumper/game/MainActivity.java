package de.spacejumper.game;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;

public class MainActivity extends Activity {
    private WebView game;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        game = new WebView(this);
        game.setBackgroundColor(0xff121d47);
        game.getSettings().setJavaScriptEnabled(true);
        game.getSettings().setDomStorageEnabled(true);
        game.getSettings().setAllowFileAccess(true);
        game.setWebChromeClient(new WebChromeClient());
        game.setWebViewClient(new WebViewClient());
        setContentView(game);
        game.loadUrl("file:///android_asset/index.html");
    }
    @Override public void onBackPressed() {
        if (game != null) game.evaluateJavascript("if(typeof pause==='function'){pause()}else{var p=document.getElementById('pause');if(p)p.click()}", null);
        else super.onBackPressed();
    }
    @Override protected void onPause() { if(game != null)game.evaluateJavascript("document.dispatchEvent(new Event('visibilitychange'))", null); super.onPause(); }
    @Override protected void onDestroy() { if(game != null){game.destroy();game=null;} super.onDestroy(); }
}

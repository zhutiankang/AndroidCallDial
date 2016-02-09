package com.example.mycall0.tab4;

import com.example.mycall0.R;
import com.example.mycall0.R.id;
import com.example.mycall0.R.layout;
import com.example.mycall0.R.menu;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWeatherActivity extends Activity {

	private WebView webView;
	private WebSettings webSettings;

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_weather);
		webView = (WebView) MyWeatherActivity.this.findViewById(R.id.myWebView);
		// 设置web属性
		webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);

		// 原有设置:是页面支持缩放
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDisplayZoomControls(true);
		webView.getSettings().setTextZoom(100);
		webView.loadUrl("http://waptianqi.2345.com/nanjing-58238.htm");
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUriLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_weather, menu);
		return true;
	}

}

package com.example.birdwatching;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class WebActivity extends Activity {
WebView webView1;
public static final String PREFS_NAME ="MyPreferencesFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		
		
		webView1=(WebView)this.findViewById(R.id.webView1);
		//get the passed url
		SharedPreferences settings= getSharedPreferences(PREFS_NAME,0);
		webView1.loadUrl(settings.getString("link", "http://www.google.com"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}

}

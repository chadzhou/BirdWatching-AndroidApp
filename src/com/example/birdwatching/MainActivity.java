package com.example.birdwatching;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity {
	Button button1;
	
	int index =5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		VideoView vv=(VideoView)this.findViewById(R.id.videoView1);
	
	
		String fileName = "android.resource://" + getPackageName() + "/" + R.raw.my_video;
		vv.setVideoURI(Uri.parse(fileName));
		vv.start();
		
		
		button1=(Button)findViewById(R.id.button1);
		button1.setEnabled(false);
		
		
		new Handler().postDelayed(new Runnable(){
			
		  
		    @Override
		    public void run()
		    {
		    	button1.setEnabled(true);
		    }
		  },5000);
		
		  
	  
		
		button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent newIntent= new Intent (MainActivity.this,BirdsInfo.class);
				startActivity(newIntent);
				finish();
			}
		});
		
		
		 
		
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

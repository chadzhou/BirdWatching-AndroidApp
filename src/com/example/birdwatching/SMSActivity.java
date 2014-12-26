package com.example.birdwatching;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SMSActivity extends Activity {
Button button1;
EditText editText1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		button1=(Button)this.findViewById(R.id.button1);
		editText1=(EditText)this.findViewById(R.id.editText1);
		
		button1.setOnClickListener(new OnClickListener() {
			   
			   @Override
			   public void onClick(View v) {
		   //use the text engine to text
			    Intent sms= new Intent(Intent.ACTION_VIEW);
			    sms.setType("vnd.android-dir/mms-sms");
			    sms.setData(Uri.parse("sms: 123456789"));
			    sms.putExtra("sms_body", editText1.getText().toString());
			    
			    startActivity(sms);
			    finish();
			    
			   }
		  });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sm, menu);
		return true;
		
		
	}

}
